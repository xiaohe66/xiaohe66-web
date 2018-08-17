package com.xiaohe66.web.org.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.ParamFinal;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.common.util.HtmlUtils;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.common.util.WebUtils;
import com.xiaohe66.web.org.dao.UsrDao;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.sys.helper.SysCfgHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class UsrService extends AbstractService<Usr> {

    private static final Logger LOG = LoggerFactory.getLogger(UsrService.class);

    /**
     * 2M
     */
    private static final int USR_HEAD_IMG_MAX_BYTE_LENGTH = 1024*1024*2;

    private UsrDao usrDao;

    @Autowired
    private UsrFileService usrFileService;

    public UsrService(){

    }

    @Autowired
    public UsrService(UsrDao usrDao) {
        super(usrDao);
        this.usrDao = usrDao;
    }

    @Override
    public void add(Usr po, Long currentUsrId) {
        Check.notEmptyCheck(po);
        if(Check.isNull(po.getImgFileId())){
            String defaultImgFileId = SysCfgHelper.getValue(ParamFinal.DEFAULT_USR_IMG_FILE_ID);
            po.setImgFileId(StrUtils.toLong(defaultImgFileId));
        }
        super.add(po,currentUsrId);
    }

    @Override
    public void updateById(Usr po, Long currentUsrId) {
        Check.notEmptyCheck(po,currentUsrId);
        String signature = HtmlUtils.delHtmlTag(po.getSignature());
        po.setSignature(signature);
        super.updateById(po, currentUsrId);
        //todo:更新用户信息后要直接刷新可看
    }

    public Usr findByUsrName(String usrName){
        Check.notEmptyCheck(usrName);
        return usrDao.findByUsrName(usrName);
    }

    public Usr findByUsrNameAndPwd(String usrName,String usrPwd){
        if(StrUtils.isAllNotEmpty(usrName,usrPwd)){
            throw new NullPointerException("usrName or usrPwd is null");
        }
        return usrDao.findByUsrNameAndPwd(usrName,usrPwd);
    }

    public void addDefaultUsrRole(Long usrId){
        if(Check.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        String cfgKeysStr = SysCfgHelper.getValue(ParamFinal.DEFAULT_ROLE_IDS_KEY);
        String[] roleStrIds =  cfgKeysStr.split(",");
        Long[] roleIds = StrUtils.toLongNotException(roleStrIds);
        this.addUsrRoles(usrId,roleIds);
    }

    public void addUsrRoles(Long usrId,Long[] roleIds){
        if(Check.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        if(Check.isNull(roleIds) || roleIds.length == 0){
            throw new XhException(CodeEnum.PARAM_ERR,"roleIds is null or size is 0");
        }
        usrDao.addUsrRoles(usrId,roleIds);
    }

    /**
     * 获取被查看用户的信息
     * @param usrId     被查看的用户id，若传入null，则默认使用站长的
     * @return  UsrDto
     */
    public UsrDto lookAtUsr(Long usrId){
        if(Check.isNull(usrId)){
            String usrIdStr = SysCfgHelper.getValue(ParamFinal.CFG_KEY_XIAO_HE_USR_ID);
            usrId = StrUtils.toLong(usrIdStr);
        }
        Usr usr = findById(usrId);
        UsrDto usrDto = ClassUtils.convert(UsrDto.class,usr);

        usrDto.setImgFileId(usr.getImgFileId());
        return usrDto;
    }

    public Long uploadHeadImg(MultipartFile file, String md5, Long currentUsrId){
        if(Check.isNull(file)){
            throw new XhException(CodeEnum.NULL_EXCEPTION,"file is null");
        }
        try {
            int bytes = file.getBytes().length;
            if(bytes > USR_HEAD_IMG_MAX_BYTE_LENGTH){
                throw new XhException(CodeEnum.MAX_VALUE_EXCEPTION);
            }
        } catch (IOException e) {
            throw new XhException(CodeEnum.IO_EXCEPTION,e);
        }

        LOG.debug("上传头像开始:md5="+md5+",usrId="+currentUsrId);
        Long imgFileId = usrFileService.uploadHeadImgFile(currentUsrId,file,md5).getId();

        Usr usr = new Usr();
        usr.setId(currentUsrId);
        usr.setImgFileId(imgFileId);
        this.updateById(usr,currentUsrId);

        UsrDto usrDto = (UsrDto) WebUtils.getSession().getAttribute(ParamFinal.SESSION_UER_KEY);
        usrDto.setImgFileId(imgFileId);

        LOG.debug("上传头像结束:md5="+md5+",usrId="+currentUsrId);
        return imgFileId;
    }

    /**
     * 用户名是否存在
     * @return 存在返回true，不存在返回false
     */
    public boolean isExist(String usrName){
        return this.findByUsrName(usrName) != null;
    }
}
