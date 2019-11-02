package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.security.mapper.UserRoleMapper;
import com.xiaohe66.web.code.security.po.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 用户角色关联service
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 16:54
 */
@Service
public class UserRoleService extends AbstractService<UserRoleMapper, UserRole> {

    private RoleService roleService;

    public UserRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void addDefaultUserRole(Integer userId) {
        this.addUserRoles(userId, roleService.listDefaultRoleId());
    }

    public void addUserRoles(Integer userId, Set<Integer> roleIdSet) {

        Check.notEmpty(userId,"userId");
        Check.notEmpty(roleIdSet,"roleIdSet");

        baseMapper.addUserRoles(userId, roleIdSet);
    }
}
