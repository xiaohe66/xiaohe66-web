package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.security.mapper.UserRoleMapper;
import com.xiaohe66.web.code.security.po.UserRole;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

        Check.notEmpty(userId, "userId");
        Check.notEmpty(roleIdSet, "roleIdSet");

        baseMapper.addUserRoles(userId, roleIdSet);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result updateByUserId(Integer userId, int[] roleIdArr) {

        UserRoleService userRoleService = (UserRoleService) AopContext.currentProxy();
        userRoleService.removeByMap(Collections.singletonMap("user_id", userId));

        if (roleIdArr.length > 0) {
            List<UserRole> newUserRoleList = Arrays.stream(roleIdArr)
                    .mapToObj(roleId -> {
                        UserRole userRole = new UserRole();
                        userRole.setUserId(userId);
                        userRole.setRoleId(roleId);
                        return userRole;
                    }).collect(Collectors.toList());

            userRoleService.saveBatch(newUserRoleList);
        }

        return Result.ok();
    }

    public Set<Integer> listRoleByUserId(Integer userId) {
        return baseMapper.listRoleByUserId(userId);
    }

}
