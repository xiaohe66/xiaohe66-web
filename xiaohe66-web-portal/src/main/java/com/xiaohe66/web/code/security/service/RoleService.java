package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.security.dao.RoleDao;
import com.xiaohe66.web.code.security.po.Role;
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


    public RoleService() {
    }

    @Autowired
    public RoleService(RoleDao roleDao) {
        super(roleDao);
        this.roleDao = roleDao;
    }

    public Set<String> findRoleNameByUsrId(Long usrId){
        return roleDao.findRoleNameByUsrId(usrId);
    }

}
