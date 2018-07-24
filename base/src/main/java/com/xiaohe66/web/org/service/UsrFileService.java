package com.xiaohe66.web.org.service;

import com.xiaohe66.web.comm.po.CommonFile;
import com.xiaohe66.web.comm.service.CommonFileService;
import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.common.util.EncoderUtils;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.org.dao.UsrFileDao;
import com.xiaohe66.web.org.dto.UsrFileDto;
import com.xiaohe66.web.org.param.UsrFileParam;
import com.xiaohe66.web.org.po.UsrFile;
import com.xiaohe66.web.org.po.UsrFileLog;
import com.xiaohe66.web.sys.service.SysCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author xh
 * @date 18-03-12 012
 */
@Service
public class UsrFileService extends AbstractService<UsrFile>{

    private static final Logger LOG = LoggerFactory.getLogger(UsrFileService.class);

    private static final Set<String> IMG_TYPE_SET = new HashSet<>(Arrays.asList(".png",".jpg",".jpeg",".bmp"));

    private static final char[] FILE_ILLEGAL_CHARS = new char[]{'?','\\','/','*','\"',':','<','>','|'};

    private static final int DEFAULT_FILE_TYPE = 0;

    private static final int USR_HEAD_IMG_FILE_TYPE = 1;

    private static final int FILE_NAME_MAX_LENGTH = 20;

    private static final int FILE_EXTENSION_MAX_LENGTH = 8;

    private UsrFileDao usrFileDao;

    @Autowired
    private CommonFileService commonFileService;

    @Autowired
    private UsrFileLogService usrFileLogService;

    @Autowired
    private SysCfgService cfgService;

    @Autowired
    private UsrService usrService;

    public UsrFileService() {}

    @Autowired
    public UsrFileService(UsrFileDao usrFileDao) {
        super(usrFileDao);
        this.usrFileDao = usrFileDao;
    }

    public Set<Integer> uploadDefaultFilePrepare(Long currentUsrId,String md5,Float mb,String fileName,String extension){
        return uploadFilePrepare(currentUsrId,md5,mb,fileName,extension,DEFAULT_FILE_TYPE);
    }

    @Transactional(rollbackFor = Exception.class)
    public UsrFileDto uploadHeadImgFile(Long currentUsrId,MultipartFile multipartFile,String md5){
        CommonFile commonFile = commonFileService.uploadFileDefault(currentUsrId,multipartFile,md5);

        String name = multipartFile.getOriginalFilename();
        int dotIndex =  name.lastIndexOf(".");
        String fileName = fileNameFormat(name.substring(0,dotIndex));
        String extension = fileExtensionFormat(name.substring(dotIndex));

        UsrFile usrFile = new UsrFile();
        usrFile.setFileId(commonFile.getId());
        usrFile.setFileType(USR_HEAD_IMG_FILE_TYPE);
        usrFile.setFileName(fileName);
        usrFile.setExtension(extension);

        add(usrFile,currentUsrId);

        return ClassUtils.convert(UsrFileDto.class,usrFile);
    }

    @Transactional(rollbackFor = Exception.class)
    public Set<Integer> uploadFilePrepare(Long currentUsrId,String md5,Float mb,String fileName,String extension,Integer fileType){
        Check.notEmptyCheck(currentUsrId,fileName);

        if(fileType != DEFAULT_FILE_TYPE && fileType != USR_HEAD_IMG_FILE_TYPE){
            throw new XhException(CodeEnum.PARAM_ERR);
        }

        Set<Integer> notUploadChunkSet = commonFileService.uploadFilePrepare(md5,mb);

        CommonFile commonFile = commonFileService.findByMd5(md5);

        /*
        * 文件已上传完或当前用户从未上传过该文件时，才增加该用户对该文件的关联
        * 即：当前用户在过去时间上传过该该文件，但未上传完成。再次上传该文件时，不再生成文件关联。
        * */
//        if(commonFile.getEndTime()!=null){
            UsrFile usrFile = new UsrFile();
            usrFile.setFileName(fileNameFormat(fileName));
            usrFile.setExtension(fileExtensionFormat(extension));
            usrFile.setFileType(fileType);
            usrFile.setFileId(commonFile.getId());
            add(usrFile,currentUsrId);
//        }

        return notUploadChunkSet;
    }

    public UsrFile findByCommonFileId(Long commonFileId){
        if(Check.isOneNull(commonFileId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"commonFileId is null");
        }
        return usrFileDao.findByCommonFileId(commonFileId);
    }

    public List<UsrFileDto> findDtoByUsrId(Long usrId){
        if(Check.isOneNull(usrId)){
            throw new XhException(CodeEnum.NULL_EXCEPTION);
        }
        UsrFileParam param = new UsrFileParam();
        param.setCreateId(usrId);

        List<UsrFile> usrFileList = this.findByParam(param);

        return ClassUtils.convertList(UsrFileDto.class,usrFileList,(usrFileDto,usrFile)->{
            CommonFile commonFile = commonFileService.findById(usrFile.getFileId());

            usrFileDto.setIsFinish(commonFile.getEndTime()!=null);

            Integer size = commonFile.getFileByte();
            //todo:需转成可视化单位
            usrFileDto.setFileSize(size+"字节");
        });
    }

    public List<UsrFileDto> findDtoAll(String search,boolean onlyWebmaster){

        UsrFileParam param = new UsrFileParam();
        if(onlyWebmaster){
            param.setCreateId(cfgService.findXhUsrId());
        }
        if(StrUtils.isNotEmpty(search)){
            param.setFileName("%"+search+"%");
        }

        List<UsrFile> usrFileList = this.findByParam(param);

        return ClassUtils.convertList(UsrFileDto.class,usrFileList,(usrFileDto,usrFile)->{
            Integer size = commonFileService.findById(usrFile.getFileId()).getFileByte();

            //todo:需转成可视化单位
            usrFileDto.setFileSize(size+"字节");
            usrFileDto.setUsrName(usrService.findById(usrFile.getCreateId()).getUsrName());
        });
    }

    public List<UsrFileDto> findDtoHotTop5(Long usrId){
        List<Map<String,Long>> mapList = usrFileLogService.countDownloadOfMonth(usrId);
        int i = 0;
        final int maxSize = 5;
        List<UsrFileDto> usrFileDtoList = new ArrayList<>(maxSize);
        for (Map<String, Long> map : mapList) {
            UsrFile usrFile = this.findById(map.get("id"));
            if(usrFile == null){
                continue;
            }
            UsrFileDto usrFileDto = ClassUtils.convert(UsrFileDto.class,usrFile);
            usrFileDtoList.add(usrFileDto);
            usrFileDto.setDownloadCount(map.get("count"));
            if(++i >= maxSize){
                break;
            }
        }
        return usrFileDtoList;
    }

    public void showImg(HttpServletResponse response,Long usrFileId){
        if(usrFileId == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"usrFileId is null");
        }

        UsrFile usrFile = findById(usrFileId);
        if(usrFile == null){
            throw new XhException(CodeEnum.RESOURCE_NOT_FOUND);
        }
        String extension = usrFile.getExtension();

        //不是图片类型，不返回
        if(!IMG_TYPE_SET.contains(extension)){
            throw new XhException(CodeEnum.IMAGE_FORMAT_EXCEPTION);
        }

        response.setContentType(ParamFinal.CONTENT_TYPE_IMAGE_PNG);
        try {
            commonFileService.outputFile(usrFile.getFileId(),response.getOutputStream());
        } catch (IOException e) {
            throw new XhException(CodeEnum.IO_EXCEPTION);
        }
    }

    /**
     * 下载文件
     * todo:需要控制不公开文件的下载权限
     *
     * @param response HttpServletResponse
     * @param usrFileId 用户文件id
     */
    public void downloadFile(HttpServletResponse response,Long usrFileId,Long currentUsrId){
        if(usrFileId == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"usrFileId is null");
        }
        if(currentUsrId == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"currentUsrId is null");
        }

        UsrFile usrFile = findById(usrFileId);
        if(usrFile == null){
            throw new XhException(CodeEnum.RESOURCE_NOT_FOUND);
        }

        String name = EncoderUtils.urlEncoder(usrFile.getFileName())+usrFile.getExtension();

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename="+name);
        try {
            commonFileService.outputFile(usrFile.getFileId(),response.getOutputStream());
        } catch (IOException e) {
            throw new XhException(CodeEnum.IO_EXCEPTION);
        }

        //记录下载日志
        usrFileLogService.add(new UsrFileLog(usrFileId),currentUsrId);
    }

    public void updateNameById(Long fileId,String fileName,Long currentUsrId){
        fileName = fileNameFormat(fileName);
        Check.notEmptyCheck(fileId,fileName,currentUsrId);
        updateById(new UsrFile(fileId,fileName),currentUsrId);
    }

    /**
     * 文件名格式化
     * 超过20个字符时，截取前32个字符。
     * 此外，如果出现非法字符，则会抛出异常
     *
     * @param fileName 文件名
     * @return  格式化后的文件名
     */
    protected  String fileNameFormat(String fileName){
        fileName = StrUtils.trim(fileName);
        Check.notEmptyCheck(fileName);
        for (char fileIllegalChar : FILE_ILLEGAL_CHARS) {
            if (fileName.contains(String.valueOf(fileIllegalChar))) {
                throw new XhException(CodeEnum.ILLEGAL_CHAR_EXCEPTION);
            }
        }
        //文件名字符长度不能超过20
        return fileName.length() > FILE_NAME_MAX_LENGTH ? fileName.substring(0,FILE_NAME_MAX_LENGTH):fileName;
    }

    /**
     * 文件名格式化
     * 超过20个字符时，截取前20个字符。
     * 此外，如果出现非法字符，则会抛出异常
     * @param extension 文件名
     * @return  格式化后的文件名
     */
    protected  String fileExtensionFormat(String extension){
        extension = StrUtils.trim(extension);
        Check.notEmptyCheck(extension);
        for (char fileIllegalChar : FILE_ILLEGAL_CHARS) {
            if (extension.contains(String.valueOf(fileIllegalChar))) {
                throw new XhException(CodeEnum.ILLEGAL_CHAR_EXCEPTION);
            }
        }
        //文件名字符长度不能超过20
        return extension.length() > FILE_EXTENSION_MAX_LENGTH ? extension.substring(0,FILE_EXTENSION_MAX_LENGTH):extension;
    }

}
