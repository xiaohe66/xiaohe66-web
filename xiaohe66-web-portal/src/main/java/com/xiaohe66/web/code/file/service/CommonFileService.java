package com.xiaohe66.web.code.file.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhIoException;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.exception.param.IllegalParamException;
import com.xiaohe66.web.base.exception.sec.IllegalOperationException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.DateUtils;
import com.xiaohe66.web.base.util.IoUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.file.dto.UploadFilePrepareDto;
import com.xiaohe66.web.code.file.mapper.CommonFileMapper;
import com.xiaohe66.web.code.file.po.CommonFile;
import com.xiaohe66.web.code.file.po.CommonFileTmp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 文件service
 * 规定最大只支持1GB的文件
 *
 * @author xh
 * @time 18-03-22 022
 */
@Service
@Slf4j
public class CommonFileService extends AbstractService<CommonFileMapper, CommonFile> {

    public static final String UPLOAD_FILE_MD5_SESSION_KEY = "uploadFileMd5";

    public static final String CURRENT_FILE_MAX_CHUNK_SESSION_KEY = "currentFileMaxChunk";

    public static final String CACHE_FILE_MD5_SESSION_KEY = "cacheFileMd5";

    @Value("${file.home}")
    private String fileHomeUrl;

    @Value("${maxMbChunkPer}")
    private Integer maxMbChunkPer;

    @Value("${maxMbFilePer}")
    private Integer maxMbFilePer;

    private CommonFileTmpService commonFileTmpService;

    public CommonFileService(CommonFileTmpService commonFileTmpService) {
        this.commonFileTmpService = commonFileTmpService;
    }

    @Override
    public boolean save(CommonFile po) {
        // name 的最大长度
        String name = po.getName();
        if (name != null && name.length() > 200) {
            po.setName(name.substring(0, 200));
        }

        return super.save(po);
    }

    private void saveSessionPrepareId(Integer id) {
        Set<Integer> cache = WebUtils.getSessionAttr(CACHE_FILE_MD5_SESSION_KEY);
        if (cache == null) {
            cache = new HashSet<>();
            WebUtils.setSessionAttr(CACHE_FILE_MD5_SESSION_KEY, cache);
        }
        cache.add(id);
    }

    private void mergeTmpFile(String md5) throws IOException, XhIoException {

        String path = createLogicPath(md5);

        File fullFile = new File(fileHomeUrl + path);

        //整合临时文件到主文件
        Files.deleteIfExists(fullFile.toPath());

        List<CommonFileTmp> commonFileTmpList = commonFileTmpService.findFileTmp(md5);
        for (CommonFileTmp commonFileTmp : commonFileTmpList) {
            File tmpFile = new File(fileHomeUrl + commonFileTmp.getFileUrl());
            IoUtils.writeToFile(tmpFile, fullFile, true);
        }

        String serviceMd5 = IoUtils.md5Sex(fullFile);

        if (!md5.equalsIgnoreCase(serviceMd5)) {
            log.error("合并后的文件md5和提供的md5不一致, 合并后的 : {}, 提供的 : {}", serviceMd5, md5);

            // 删除临时文件
            try {
                commonFileTmpService.delByMd5(md5);
            } catch (Exception e) {
                log.error("无法删除临时文件", e);
            }

            throw new IllegalOperationException("合并后的md5与提供的不一致");
        }

        //更新主文件状态
        this.updateByMd5(md5, new Date(), path, fullFile.length());

        //删除临时文件
        try {
            commonFileTmpService.delByMd5(md5);
        } catch (Exception e) {
            log.error("删除临时文件失败, md5 : {}", md5, e);
        }

        WebUtils.removeSessionAttr(UPLOAD_FILE_MD5_SESSION_KEY);
        WebUtils.removeSessionAttr(CURRENT_FILE_MAX_CHUNK_SESSION_KEY);
    }

    /**
     * 分块上传准备，返回未上传的文件区块
     * <p>
     * 文件已上传完成：返回一个空集合
     * 文件未上传完成：返回未上传完成的区块
     *
     * @param md5 文件的md5值
     * @param mb  文件大小，单位mb
     * @return 返回未上传的文件区块，全部上传完成时，返回一个空集合
     */
    public UploadFilePrepareDto uploadFilePrepare(String md5, Float mb) {
        final int md5Length = 32;
        if (md5 == null || md5.length() != md5Length) {
            throw new XhWebException(CodeEnum.B1_ILLEGAL_PARAM, "md5为null，或长度不为" + md5Length + "位");
        }
        if (mb == null || mb <= 0 || mb > maxMbFilePer) {
            throw new IllegalParamException("mb小于0或大于最大值, mb=" + mb);
        }
        log.info("文件上传准备, md5 : {}, 大小 : {}MB", md5, mb);

        // todo :  如果刚好除尽没有余数时，会出现异常吗？
        int currentMaxChunk = mb.intValue() / maxMbChunkPer + 1;

        WebUtils.setSessionAttr(UPLOAD_FILE_MD5_SESSION_KEY, md5);
        WebUtils.setSessionAttr(CURRENT_FILE_MAX_CHUNK_SESSION_KEY, currentMaxChunk);

        UploadFilePrepareDto uploadFilePrepareDto = new UploadFilePrepareDto();
        uploadFilePrepareDto.setMaxMbChunkPer(maxMbChunkPer);
        uploadFilePrepareDto.setCountChunk(currentMaxChunk);

        CommonFile commonFile = getByMd5(md5);
        if (commonFile == null) {
            log.info("文件未上传过，生成主文件记录");

            try {
                commonFileTmpService.createTmpDirectory(md5);
            } catch (XhIoException e) {
                throw new XhWebException(CodeEnum.EXCEPTION, "无法生成临时文件夹", e);
            }

            //生成主文件记录
            commonFile = new CommonFile(md5);
            save(commonFile);

            uploadFilePrepareDto.setFileId(commonFile.getId());

            Set<Integer> missingChunkSet = new HashSet<>(currentMaxChunk);
            for (int i = 1; i <= currentMaxChunk; i++) {
                missingChunkSet.add(i);
            }
            uploadFilePrepareDto.setMissingChunk(missingChunkSet);

            saveSessionPrepareId(commonFile.getId());
            return uploadFilePrepareDto;
        }

        uploadFilePrepareDto.setFileId(commonFile.getId());
        saveSessionPrepareId(commonFile.getId());

        if (commonFile.getEndTime() != null) {
            //已经上传完成，返回空集合
            log.info("文件已上传完成, md5 : {}, 大小 : {}MB", md5, mb);
            uploadFilePrepareDto.setMissingChunk(Collections.emptySet());
            return uploadFilePrepareDto;

        } else {
            //有主文件记录，但未上传完成，返回未上传完的块
            Set<Integer> finishChunk = commonFileTmpService.findFinishChunk(md5);
            if (finishChunk.size() >= currentMaxChunk) {
                log.warn("所有块都已上传完成，但是EndTime没有更新，自动更新EndTime, md5 : {}", md5);
                try {
                    mergeTmpFile(md5);
                    log.warn("自动合并文件成功, md5 : {}", md5);
                } catch (IOException | XhIoException e) {
                    log.error("尝试合并文件失败, md5 : {}", md5, e);
                }
                uploadFilePrepareDto.setMissingChunk(Collections.emptySet());
            } else {

                Set<Integer> missingChunkSet = new HashSet<>(currentMaxChunk);
                for (int i = 1; i <= currentMaxChunk; i++) {
                    missingChunkSet.add(i);
                }
                missingChunkSet.removeAll(finishChunk);
                uploadFilePrepareDto.setMissingChunk(missingChunkSet);
            }
            return uploadFilePrepareDto;
        }
    }

    /**
     * 断点续传型文件上传
     *
     * @param multipartFile 块
     * @param md5           主文件的md5值
     * @param chunk         当前块
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadFile(MultipartFile multipartFile, String md5, Integer chunk) {
        String sessionMd5 = WebUtils.getSessionAttr(UPLOAD_FILE_MD5_SESSION_KEY);
        if (StringUtils.isEmpty(sessionMd5)) {
            throw new XhWebException(CodeEnum.B0_ILLEGAL_REQUEST, "上传文件前没有调用prepare接口");
        }

        if (!sessionMd5.equals(md5)) {
            log.debug("上传文件md5和准备接口不一致, sessionMd5 : {}, md5 : {}", sessionMd5, md5);
            throw new XhWebException(CodeEnum.B0_ILLEGAL_REQUEST, "上传文件md5和准备接口不一致");
        }

        Check.notEmpty(chunk);
        int currentMaxChunk = WebUtils.getSessionAttr(CURRENT_FILE_MAX_CHUNK_SESSION_KEY);
        if (chunk < 1 || chunk > currentMaxChunk) {
            throw new XhWebException(CodeEnum.B0_ILLEGAL_REQUEST, "上传文件的区块不在区间内, 区块 : " + chunk);
        }

        log.info("文件上传，md5 : {}, 块 : {}", md5, chunk);

        InputStream fileInput;
        int fileByte;
        try {
            fileInput = multipartFile.getInputStream();
            fileByte = multipartFile.getBytes().length;
        } catch (IOException e) {
            throw new XhWebException(CodeEnum.EXCEPTION, "无法读取上传文件", e);
        }

        if (currentMaxChunk == 1) {
            //只有一个临时文件，直接上传至主文件
            String path = createLogicPath(md5);
            File file = new File(fileHomeUrl + path);
            try {
                IoUtils.writeToFile(fileInput, file, false);
            } catch (XhIoException e) {
                log.error("无法写入文件,path : {}", file);
                throw new XhWebException(CodeEnum.EXCEPTION, "无法写文件", e);
            }

            String serviceMd5;
            try {
                serviceMd5 = IoUtils.md5Sex(file);
            } catch (XhIoException e) {
                throw new XhWebException(CodeEnum.EXCEPTION, e);
            }

            if (!md5.equalsIgnoreCase(serviceMd5)) {
                log.error("生成的文件md5和提供的md5不一致, 生成的: {}, 提供的 : {}", serviceMd5, md5);

                throw new IllegalOperationException("服务器生成的md5与接口提供的不一致");
            }

            //更新主文件状态
            this.updateByMd5(md5, new Date(), path, fileByte);

            return true;
        }

        //上传临时文件
        commonFileTmpService.uploadTmpFile(fileInput, fileByte, md5, chunk);

        /*
         * 上传完成判断
         * 这里进行多线程控制，锁作用于md5值。即对同一md5值的操作，只能有一个进入。
         *
         * 类 String 维护一个字符串池,当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（该对象由 equals(Object) 方法确定），
         * 则返回池中的字符串，因此，当String相同时，String.intern()总是返回同一个对象
         * */
        // todo : 优化该同步块
        synchronized (md5.intern()) {
            int countFinish = commonFileTmpService.countFinish(md5);
            log.debug("总块 : {}, 当前块 : {}, 完成数量 : {}", currentMaxChunk, chunk, countFinish);
            if (countFinish >= currentMaxChunk) {
                log.info("所有块都上传完成，开始合并文件,md5 : {}", md5);
                try {
                    log.info("合并临时文件，md5 : {}", md5);
                    mergeTmpFile(md5);
                    log.info("合并临时文件完成，md5 : {}", md5);
                } catch (IOException | XhIoException e) {
                    log.error("合并文件失败, md5 : {}", md5);
                    throw new XhWebException(CodeEnum.EXCEPTION, "合并文件失败", e);
                }
            }
        }

        return true;
    }

    public void updateByMd5(String md5, Date date, String path, long fileBytes) {
        CommonFile commonFile = new CommonFile();
        commonFile.setMd5(md5);
        commonFile.setEndTime(date);
        commonFile.setFileUrl(path);
        commonFile.setFileByte(fileBytes);
        baseMapper.updateByMd5(commonFile);
    }

    public CommonFile getByMd5(String md5) {
        Check.notEmpty(md5);
        CommonFile commonFile = new CommonFile(md5);
        return getOne(new QueryWrapper<>(commonFile));
    }

    public void outputFile(OutputStream outputStream, Integer id) {
        Check.notEmpty(id);
        CommonFile commonFile = getById(id);
        outputFile(outputStream, commonFile);
    }

    public void outputFile(OutputStream outputStream, String md5) {
        Check.notEmpty(md5);
        CommonFile commonFile = getByMd5(md5);
        outputFile(outputStream, commonFile);
    }

    public void outputFile(OutputStream outputStream, CommonFile commonFile) {

        String url = fileHomeUrl + commonFile.getFileUrl();

        File file = new File(url);

        try {
            IoUtils.writeToOutput(file, outputStream);
        } catch (XhIoException e) {
            log.error("无法输出文件", e);
            throw new XhWebException(CodeEnum.EXCEPTION, e);
        }
    }

    /**
     * 根据md5值生成一个文件逻辑路径
     *
     * @param md5 文件的md5值
     * @return 格式：2018-07/a/a5f3a36d02749cc5cf049fga80727f90
     */
    public String createLogicPath(String md5) {
        return DateUtils.yyyyMM.format(new Date()) + File.separator + md5.substring(0, 1) + File.separator + md5;
    }

}
