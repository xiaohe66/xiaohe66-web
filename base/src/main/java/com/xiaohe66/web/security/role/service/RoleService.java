package com.xiaohe66.web.security.role.service;

import com.xiaohe66.web.common.base.impl.AbstractService;
import com.xiaohe66.web.security.role.dao.RoleDao;
import com.xiaohe66.web.security.role.po.Role;
import com.xiaohe66.web.sys.service.SysCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@Service
public class RoleService extends AbstractService<Role> {

    private RoleDao roleDao;

    @Autowired
    private SysCfgService sysCfgService;

    public RoleService() {
    }

    @Autowired
    public RoleService(RoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }

    public Set<String> findRoleNameByUsrId(Long usrId){
        return roleDao.searchRoleNameByUsrId(usrId);
    }

}
