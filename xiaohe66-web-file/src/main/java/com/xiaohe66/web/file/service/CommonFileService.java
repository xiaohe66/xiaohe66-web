package com.xiaohe66.web.file.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.DateUtils;
import com.xiaohe66.web.base.util.IoUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.file.dao.CommonFileDao;
import com.xiaohe66.web.file.po.CommonFile;
import com.xiaohe66.web.file.po.CommonFileTmp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * 文件service
 * 规定最大只支持1GB的文件
 *
 * @author xh
 * @date 18-03-22 022
 */
@Service
public class CommonFileService extends AbstractService<CommonFile>{

    private static final Logger LOG = LoggerFactory.getLogger(CommonFileService.class);

    private CommonFileDao commonFileDao;

    @Value("${file.home}")
    private String fileHomeUrl;

    @Value("${chunkMaxMb}")
    private Integer chunkMaxMb;

    @Value("${fileMaxMb}")
    private Integer fileMaxMb;

    @Autowired
    private CommonFileTmpService commonFileTmpService;

    public CommonFileService(){}

    @Autowired
    public CommonFileService(CommonFileDao commonFileDao) {
        super(commonFileDao);
        this.commonFileDao = commonFileDao;
    }

    /**
     * 分块上传准备，返回未上传的文件区块
     *
     * 文件已上传完成：返回一个空集合
     * 文件未上传完成：返回未上传完成的区块
     *
     * @param md5   文件的md5值
     * @param mb   文件大小，单位mb
     *
     * @return 返回未上传的文件区块，全部上传完成时，返回一个空集合
     */
    public Set<Integer> uploadFilePrepare(String md5,Float mb){
        final int md5Length = 32;
        if(md5 == null||md5.length()!= md5Length){
            throw new XhException(CodeEnum.PARAM_ERR,"md5为null，或长度不为"+ md5Length +"位");
        }
        if(mb == null||mb < 0){
            throw new XhException(CodeEnum.PARAM_ERR,"param mb is error,mb="+mb);
        }
        if(mb > fileMaxMb){
            throw new XhException(CodeEnum.PARAM_ERR,"param mb cannot be greater than fileMaxMb:mb="+mb+",fileMaxMb="+fileMaxMb);
        }
        int chunkCount = mb.intValue()/chunkMaxMb+1;

        WebUtils.setSessionAttr("md5",md5);
        WebUtils.setSessionAttr("chunkCount",chunkCount);

        CommonFile commonFile = findByMd5(md5);

        Set<Integer> resultSet = new HashSet<>(chunkCount);
        for (int i = 1; i <= chunkCount; i++) {
            resultSet.add(i);
        }

        if(commonFile == null){
            LOG.info("文件未上传过，生成主文件记录");
            //生成主文件记录
            commonFile = new CommonFile();
            commonFile.setMd5(md5);
            commonFile.setStartTime(new Date());
            this.add(commonFile,null);

            return resultSet;
        }

        if(commonFile.getEndTime()!=null){
            //已经上传完成，返回空集合
            return new HashSet<>(0);
        }else{
            //有主文件记录，但未上传完成，返回未上传完的块
            resultSet.removeAll(commonFileTmpService.findFinishChunk(md5));
            return resultSet;
        }
    }

    /**
     * 断点续传型文件上传
     * @param multipartFile 块
     * @param md5           主文件的md5值
     * @param chunk         当前块
     * @return              是否成功
     * @throws IOException  IOException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadFile(MultipartFile multipartFile, String md5, Integer chunk) throws IOException {
        String sessionMd5 = WebUtils.getSessionAttr("md5");
        if(sessionMd5 == null||sessionMd5.length()==0){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"没有调用prepare接口");
        }

        if (!sessionMd5.equals(md5)) {
            throw new XhException(CodeEnum.NULL_EXCEPTION,"参数md5和准备接口不一致");
        }

        Check.notEmptyCheck(chunk);
        int chunkCount = WebUtils.getSessionAttr("chunkCount");
        if(chunk<1 || chunk>chunkCount){
            throw new XhException(CodeEnum.PARAM_ERR,"上传的块数不在区间内");
        }


        InputStream fileInput = multipartFile.getInputStream();
        int fileByte = multipartFile.getBytes().length;

        if(chunkCount == 1){
            //只有一个临时文件，直接上传至主文件
            String path = createLogicPath(md5);
            IoUtils.writeToFile(fileInput,new File(fileHomeUrl+path),false);

            //更新主文件状态
            CommonFile commonFile = new CommonFile();
            commonFile.setMd5(md5);
            commonFile.setEndTime(new Date());
            commonFile.setFileUrl(path);
            commonFile.setFileByte(fileByte);
            this.updateByMd5(commonFile);

            return true;
        }

        //上传临时文件
        commonFileTmpService.uploadTmpFile(fileInput,fileByte,md5,chunk);

        /*
        * 上传完成判断
        * 这里进行多线程控制，锁作用于md5值。即对同一md5值的操作，只能有一个进入。
        *
        * 类 String 维护一个字符串池,当调用 intern 方法时，如果池已经包含一个等于此 String 对象的字符串（该对象由 equals(Object) 方法确定），
        * 则返回池中的字符串，因此，当String相同时，String.intern()总是返回同一个对象
        * */
        synchronized (md5.intern()){
            int countFinish = commonFileTmpService.countFinish(md5);
            if(countFinish == chunkCount){
                String path = createLogicPath(md5);

                File fileFull = new File(fileHomeUrl+path);
                List<CommonFileTmp> commonFileTmpList = commonFileTmpService.findFileTmp(md5);

                //整合临时文件到主文件
                int byteSum=0;
                if (fileFull.exists()) {
                    fileFull.delete();
                }
                for (CommonFileTmp commonFileTmp : commonFileTmpList) {
                    IoUtils.writeToFile(new File(fileHomeUrl+commonFileTmp.getFileUrl()),fileFull,true);
                    byteSum += commonFileTmp.getFileByte();
                }

                //更新主文件状态
                CommonFile commonFile = new CommonFile();
                commonFile.setMd5(md5);
                commonFile.setEndTime(new Date());
                commonFile.setFileUrl(path);
                commonFile.setFileByte(byteSum);
                this.updateByMd5(commonFile);

                //删除临时文件
                commonFileTmpService.delByMd5(md5);

                WebUtils.removeSessionAttr("md5");
                WebUtils.removeSessionAttr("chunkCount");
            }
        }

        return true;
    }

    /**
     * 小文件上传
     * <p>该方法只能用于小文件的上传，文件会被直接写入对应的目录，并生成文件的主记录
     *
     * @param multipartFile 文件
     * @param md5           文件的md5值
     */
    public CommonFile uploadFileDefault(Long currentUsrId,MultipartFile multipartFile, String md5){
        Check.notEmptyCheck(currentUsrId,multipartFile,md5);

        CommonFile commonFile = findByMd5(md5);
        if(commonFile != null && commonFile.getEndTime() != null){
            return commonFile;
        }

        String fileUrl = createLogicPath(md5);
        InputStream fileInput;
        try {
             fileInput = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new XhException(CodeEnum.IO_EXCEPTION,e);
        }
        IoUtils.writeToFile(fileInput,new File(fileHomeUrl+fileUrl),false);
        int fileBytes;
        try {
            fileBytes = multipartFile.getBytes().length;
        } catch (IOException e) {
            throw new XhException(CodeEnum.IO_EXCEPTION,e);
        }

        commonFile = new CommonFile();
        commonFile.setFileByte(fileBytes);
        commonFile.setFileUrl(fileUrl);
        commonFile.setEndTime(new Date());
        commonFile.setMd5(md5);

        add(commonFile,currentUsrId);

        return commonFile;
    }

    public void updateByMd5(CommonFile commonFile){
        this.updateByParam(commonFile,commonFile,null);
    }

    public void outputFile(Long id,OutputStream outputStream){
        Check.notEmptyCheck(id);
        CommonFile commonFile = findById(id);
        String url = fileHomeUrl + commonFile.getFileUrl();

        IoUtils.writeToOutput(new File(url),outputStream);
    }

    /**
     * 判断一个文件是否存在于服务器中
     * @param md5   文件的md5摘要
     * @return  返回true表示存在，false表示不存在
     */
    public boolean isExist(String md5){
        return findByMd5(md5) != null;
    }

    public CommonFile findByMd5(String md5){
        Check.notEmptyCheck(md5);
        return commonFileDao.findByMd5(md5);
    }


    /**
     * 根据md5值生成一个文件逻辑路径
     * @param md5   文件的md5值
     * @return  格式：2018-07/a/a5f3a36d02749cc5cf049fga80727f90
     */
    private String createLogicPath(String md5){
        return DateUtils.format("yyyy-MM")+File.separator+md5.substring(0,1)+File.separator+md5;
    }

}
