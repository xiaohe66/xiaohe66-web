package com.xiaohe66.web.code.security.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.security.mapper.RolePermissionMapper;
import com.xiaohe66.web.code.security.po.RolePermission;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @time 2019.10.21 11:14
 */
@Service
public class RolePermissionService extends AbstractService<RolePermissionMapper, RolePermission> {


    public Set<Integer> listPermissionIdInRoleId(Integer roleId) {

        return baseMapper.listPermissionIdInRoleId(roleId);
    }

    @Transactional(rollbackFor = Exception.class)
    public Result updateByRoleId(Integer roleId, int[] permissionIdArr) {

        RolePermissionService rolePermissionService = (RolePermissionService) AopContext.currentProxy();

        rolePermissionService.removeByMap(Collections.singletonMap("role_id", roleId));

        List<RolePermission> list = Arrays.stream(permissionIdArr)
                .mapToObj(id -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(id);
                    return rolePermission;
                })
                .collect(Collectors.toList());

        rolePermissionService.saveBatch(list);

        return Result.ok();
    }

}
