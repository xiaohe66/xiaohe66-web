package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.exception.XhException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.base.util.StrUtils;
import com.xiaohe66.web.code.security.dao.UsrRoleDao;
import com.xiaohe66.web.code.security.po.UsrRole;
import com.xiaohe66.web.code.sys.helper.SysCfgHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * 用户角色关联service
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 16:54
 */
@Service
public class UsrRoleService extends AbstractService<UsrRoleDao,UsrRole>{

    public void addDefaultUsrRole(Integer usrId){
        if(Check.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        //xh todo:需要优化掉string转Integer代码
        String cfgKeysStr = SysCfgHelper.getString(Final.Str.DEFAULT_ROLE_IDS_KEY);
        String[] roleStrIds =  cfgKeysStr.split(",");
        Integer[] roleIds = new Integer[roleStrIds.length];
        for (int i = 0; i < roleStrIds.length; i++) {
            roleIds[i] = StrUtils.toIntNotException(roleStrIds[i]);
        }
        this.addUsrRoles(usrId,roleIds);
    }

    public void addUsrRoles(Integer usrId, Integer[] roleIds){
        if(Check.isNull(usrId)){
            throw new XhException(CodeEnum.PARAM_ERR,"usrId is null");
        }
        if(Check.isNull(roleIds) || roleIds.length == 0){
            throw new XhException(CodeEnum.PARAM_ERR,"roleIds is null or size is 0");
        }
        baseMapper.addUsrRoles(usrId,roleIds);
    }
}
