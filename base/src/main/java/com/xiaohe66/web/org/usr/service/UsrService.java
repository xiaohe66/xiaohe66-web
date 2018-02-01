package com.xiaohe66.web.org.usr.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.common.data.CheckUtils;
import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.data.StrEnum;
import com.xiaohe66.web.common.exception.XhException;
import com.xiaohe66.web.common.util.StrUtils;
import com.xiaohe66.web.org.usr.dao.UsrDao;
import com.xiaohe66.web.org.usr.po.Usr;
import com.xiaohe66.web.security.role.service.RoleService;
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

    public UsrService(){

    }

    @Autowired
    public UsrService(UsrDao usrDao) {
        super(usrDao);
        this.usrDao = usrDao;
    }

    public Usr findByUsrName(String usrName){
        return usrDao.searchByUsrName(usrName);
    }

    public Usr findByUsrNameAndPwd(String usrName,String usrPwd){
        if(StrUtils.isAllNotEmpty(usrName,usrPwd)){
            throw new NullPointerException("usrName or usrPwd is null");
        }
        return usrDao.searchByUsrNameAndPwd(usrName,usrPwd);
    }

    public void addDefaultUsrRole(Long usrId){
        if(CheckUtils.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        String cfgKeysStr = sysCfgService.searchValByName(StrEnum.DEFAULT_ROLE_IDS_KEY.data());
        String[] roleStrIds =  cfgKeysStr.split(",");
        Long[] roleIds = StrUtils.toLongNotException(roleStrIds);
        this.addUsrRoles(usrId,roleIds);
    }

    public void addUsrRoles(Long usrId,Long[] roleIds){
        if(CheckUtils.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        if(CheckUtils.isNull(roleIds) || roleIds.length == 0){
            throw new XhException(CodeEnum.PARAM_ERR,"roleIds is null or size is 0");
        }
        usrDao.addUsrRoles(usrId,roleIds);
    }
}
