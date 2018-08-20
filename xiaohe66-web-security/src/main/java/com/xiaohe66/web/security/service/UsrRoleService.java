package com.xiaohe66.web.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.ParamFinal;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.security.dao.UsrRoleDao;
import com.xiaohe66.web.sys.helper.SysCfgHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户角色关联service
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 16:54
 */
@Service
public class UsrRoleService extends AbstractService{

    private UsrRoleDao usrRoleDao;

    public UsrRoleService() {
    }

    @Autowired
    public UsrRoleService(UsrRoleDao usrRoleDao) {
        super(usrRoleDao);
        this.usrRoleDao = usrRoleDao;
    }

    public void addDefaultUsrRole(Long usrId){
        if(Check.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        //xh todo:需要优化掉string转long代码
        String cfgKeysStr = SysCfgHelper.getString(ParamFinal.DEFAULT_ROLE_IDS_KEY);
        String[] roleStrIds =  cfgKeysStr.split(",");
        Long[] roleIds = StrUtils.toLongNotException(roleStrIds);
        this.addUsrRoles(usrId,roleIds);
    }

    public void addUsrRoles(Long usrId, Long[] roleIds){
        if(Check.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        if(Check.isNull(roleIds) || roleIds.length == 0){
            throw new XhException(CodeEnum.PARAM_ERR,"roleIds is null or size is 0");
        }
        usrRoleDao.addUsrRoles(usrId,roleIds);
    }
}
