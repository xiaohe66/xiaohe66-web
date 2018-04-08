package com.xiaohe66.web.org.service;

import com.xiaohe66.web.comm.po.CommonFile;
import com.xiaohe66.web.comm.service.CommonFileService;
import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.org.dao.UsrFileDao;
import com.xiaohe66.web.org.dto.UsrFileDto;
import com.xiaohe66.web.org.param.UsrFileParam;
import com.xiaohe66.web.org.po.UsrFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author xh
 * @date 18-03-12 012
 */
@Service
public class UsrFileService extends AbstractService<UsrFile>{

    private UsrFileDao usrFileDao;

    @Autowired
    private CommonFileService commonFileService;

    public UsrFileService() {}

    @Autowired
    public UsrFileService(UsrFileDao usrFileDao) {
        super(usrFileDao);
        this.usrFileDao = usrFileDao;
    }

    /**
     * 上传用户文件，返回commonFile的id
     * @param file  文件
     * @param md5   文件的md5摘要
     * @param currentUsrId  当前操作用户
     * @return 返回commonFile的id
     */
    public Long uploadFile(MultipartFile file,String md5,Long currentUsrId){
        if(Check.isNull(file)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"file is null");
        }
        if(Check.isNull(currentUsrId)){
            throw new XhException(CodeEnum.NOT_LOGGED_IN,"not login");
        }
        if(StrUtils.isEmpty(md5)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"md5 is empty");
        }

        byte[] bytes = null;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new XhException(CodeEnum.IO_EXCEPTION);
        }
        CommonFile commonFile = commonFileService.uploadFile(bytes,md5);
        UsrFile usrFile = findByCommonFileId(commonFile.getId());
        if(usrFile == null){
            //todo:这里的判断，会不会有多线程的问题？导致数据库有多条记录？
            usrFile = new UsrFile();
            usrFile.setFileId(commonFile.getId());
            usrFile.setFileName(file.getOriginalFilename());
            this.add(usrFile,currentUsrId);
        }

        return usrFile.getFileId();
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

        return ClassUtils.convertList(UsrFileDto.class,this.findByParam(param));
    }

}
