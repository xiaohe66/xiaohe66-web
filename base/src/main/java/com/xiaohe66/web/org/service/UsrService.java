package com.xiaohe66.web.org.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.util.Check;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.ClassUtils;
import com.xiaohe66.web.common.util.HtmlUtils;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.org.dao.UsrDao;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.security.service.RoleService;
import com.xiaohe66.web.sys.service.SysCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class UsrService extends AbstractService<Usr> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsrService.class);

    private UsrDao usrDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SysCfgService sysCfgService;

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
    public void updateById(Usr po, Long currentUsrId) {
        Check.notEmptyCheck(po,currentUsrId);
        String signature = HtmlUtils.delHtmlTag(po.getSignature());
        po.setSignature(signature);
        super.updateById(po, currentUsrId);
    }

    public Usr findByUsrName(String usrName){
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
        String cfgKeysStr = sysCfgService.findValByKey(StrEnum.DEFAULT_ROLE_IDS_KEY.data());
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
            String usrIdStr = sysCfgService.findValByKey(StrEnum.CFG_KEY_XIAO_HE_USR_ID.data());
            usrId = StrUtils.toLong(usrIdStr);
        }
        Usr usr = findById(usrId);
        UsrDto usrDto = ClassUtils.convert(UsrDto.class,usr);

        usrDto.setImgUrl("/org/usr/file/img/"+usr.getImgFileId());
        return usrDto;
    }
}
