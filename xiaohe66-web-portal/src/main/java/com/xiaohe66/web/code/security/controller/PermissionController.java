package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.security.dto.PermissionDto;
import com.xiaohe66.web.code.security.po.Permission;
import com.xiaohe66.web.code.security.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.21 10:39
 */
@XhController("/sec/permission")
@Slf4j
public class PermissionController extends SecurityController<PermissionService, Permission, PermissionDto> {

    @RequiresAuthentication
    @Get("/check/{name}")
    public Result check(@PathVariable String name) {
        return Result.ok(SecurityUtils.getSubject().isPermitted(name));
    }

    @RequiresAuthentication
    @Get("/all")
    public Result all() {
        checkRole(SEC_ADMIN_ROLE_NAME);

        List<Permission> permissionList = baseService.list();

        List<PermissionDto> dtoList = ClassUtils.convert(PermissionDto.class, permissionList);

        return Result.ok(dtoList);
    }

}
