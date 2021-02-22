package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.security.dto.RolePermissionDto;
import com.xiaohe66.web.code.security.po.RolePermission;
import com.xiaohe66.web.code.security.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Set;

/**
 * @author xiaohe
 * @time 2019.10.21 11:17
 */
@XhController("/sec/role/permission")
@Slf4j
public class RolePermissionController extends SecurityController<RolePermissionService, RolePermission, RolePermissionDto> {


    @RequiresAuthentication
    @Get("/role/{roleId}")
    public Result role(@PathVariable Integer roleId) {
        checkRole(SEC_ADMIN_ROLE_NAME);

        Set<Integer> roleIdSet = baseService.listPermissionIdInRoleId(roleId);

        return Result.ok(roleIdSet);
    }

    @RequiresAuthentication
    @Put("/role")
    public Result update(Integer roleId,
                         @RequestParam(value = "ids[]", required = false) int[] permissionIdArr) {

        checkRole(SEC_ADMIN_ROLE_NAME);

        if (log.isDebugEnabled()) {
            log.debug("update role permission, roleId : {}, permissionIdArr : {}", roleId, Arrays.toString(permissionIdArr));
        }

        return baseService.updateByRoleId(roleId, permissionIdArr == null ? ArrayUtils.EMPTY_INT_ARRAY : permissionIdArr);
    }

}
