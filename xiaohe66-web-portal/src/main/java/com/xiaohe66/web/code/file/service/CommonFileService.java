package com.xiaohe66.web.code.file.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(CommonFileService.class);

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
            throw new XhWebException(CodeEnum.PARAM_ERR, "md5为null，或长度不为" + md5Length + "位");
        }
        if (mb == null || mb < 0 || mb > maxMbFilePer) {
            throw new XhWebException(CodeEnum.PARAM_ERR, "param mb is error, mb=" + mb);
        }

        int currentMaxChunk = mb.intValue() / maxMbChunkPer + 1;

        WebUtils.setSessionAttr(UPLOAD_FILE_MD5_SESSION_KEY, md5);
        WebUtils.setSessionAttr(CURRENT_FILE_MAX_CHUNK_SESSION_KEY, currentMaxChunk);

        UploadFilePrepareDto uploadFilePrepareDto = new UploadFilePrepareDto();
        uploadFilePrepareDto.setMaxMbChunkPer(maxMbChunkPer);

        CommonFile commonFile = getByMd5(md5);
        if (commonFile == null) {
            LOG.info("文件未上传过，生成主文件记录");

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
            uploadFilePrepareDto.setMissingChunk(Collections.emptySet());
            return uploadFilePrepareDto;

        } else {
            //有主文件记录，但未上传完成，返回未上传完的块
            Set<Integer> finishChunk = commonFileTmpService.findFinishChunk(md5);
            if (finishChunk.size() == currentMaxChunk) {
                // todo : 处理这个问题
                log.warn("所有块都已上传完成，但是EndTime没有更新，自动更新EndTime");
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
     * @throws IOException IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadFile(MultipartFile multipartFile, String md5, Integer chunk) throws IOException {
        String sessionMd5 = WebUtils.getSessionAttr(UPLOAD_FILE_MD5_SESSION_KEY);
        if (StringUtils.isEmpty(sessionMd5)) {
            throw new XhWebException(CodeEnum.ILLEGAL_ARGUMENT_EXCEPTION, "没有调用prepare接口");
        }

        if (!sessionMd5.equals(md5)) {
            throw new XhWebException(CodeEnum.ILLEGAL_ARGUMENT_EXCEPTION, "参数md5和准备接口不一致");
        }

        Check.notEmptyCheck(chunk);
        int currentMaxChunk = WebUtils.getSessionAttr(CURRENT_FILE_MAX_CHUNK_SESSION_KEY);
        if (chunk < 1 || chunk > currentMaxChunk) {
            throw new XhWebException(CodeEnum.ILLEGAL_ARGUMENT_EXCEPTION, "上传的块数不在区间内");
        }


        InputStream fileInput = multipartFile.getInputStream();
        int fileByte = multipartFile.getBytes().length;

        if (currentMaxChunk == 1) {
            //只有一个临时文件，直接上传至主文件
            String path = createLogicPath(md5);
            IoUtils.writeToFile(fileInput, new File(fileHomeUrl + path), false);

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
        // todo : 将该代码块独立到一个方法里面，还有考虑使用线程合并文件
        synchronized (md5.intern()) {
            int countFinish = commonFileTmpService.countFinish(md5);
            if (countFinish == currentMaxChunk) {
                String path = createLogicPath(md5);

                File fullFile = new File(fileHomeUrl + path);

                //整合临时文件到主文件
                boolean isSuccess = Files.deleteIfExists(fullFile.toPath());
                if (!isSuccess) {
                    // todo : 删除已存在文件失败时的处理
                    log.warn("删除失败");
                    throw new XhWebException(CodeEnum.EXCEPTION, "删除已存在文件失败");
                }

                int byteSum = 0;
                List<CommonFileTmp> commonFileTmpList = commonFileTmpService.findFileTmp(md5);
                for (CommonFileTmp commonFileTmp : commonFileTmpList) {
                    IoUtils.writeToFile(new File(fileHomeUrl + commonFileTmp.getFileUrl()), fullFile, true);
                    byteSum += commonFileTmp.getFileByte();
                }

                // todo : 增加前端提供的 md5 和后端文件md5的一致性判断

                //更新主文件状态
                this.updateByMd5(md5, new Date(), path, byteSum);

                //删除临时文件
                commonFileTmpService.delByMd5(md5);

                WebUtils.removeSessionAttr(UPLOAD_FILE_MD5_SESSION_KEY);
                WebUtils.removeSessionAttr(CURRENT_FILE_MAX_CHUNK_SESSION_KEY);
            }
        }

        return true;
    }

    public void updateByMd5(String md5, Date date, String path, int fileBytes) {
        CommonFile commonFile = new CommonFile();
        commonFile.setMd5(md5);
        commonFile.setEndTime(date);
        commonFile.setFileUrl(path);
        commonFile.setFileByte(fileBytes);
        baseMapper.updateByMd5(commonFile);
    }

    public CommonFile getByMd5(String md5) {
        Check.notEmptyCheck(md5);
        CommonFile commonFile = new CommonFile(md5);
        return getOne(new QueryWrapper<>(commonFile));
    }

    public void outputFile(OutputStream outputStream, Integer id) {
        Check.notEmptyCheck(id);
        CommonFile commonFile = getById(id);
        outputFile(outputStream, commonFile);
    }

    public void outputFile(OutputStream outputStream, String md5) {
        Check.notEmptyCheck(md5);
        CommonFile commonFile = getByMd5(md5);
        outputFile(outputStream, commonFile);
    }

    public void outputFile(OutputStream outputStream, CommonFile commonFile) {

        String url = fileHomeUrl + commonFile.getFileUrl();

        IoUtils.writeToOutput(new File(url), outputStream);
    }

    /**
     * 根据md5值生成一个文件逻辑路径
     *
     * @param md5 文件的md5值
     * @return 格式：2018-07/a/a5f3a36d02749cc5cf049fga80727f90
     */
    public String createLogicPath(String md5) {
        // todo : 改用 FastDateFormat
        return DateUtils.format("yyyy-MM") + File.separator + md5.substring(0, 1) + File.separator + md5;
    }

}
