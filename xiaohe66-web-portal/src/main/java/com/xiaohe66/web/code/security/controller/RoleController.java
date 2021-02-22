package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.security.dto.RoleDto;
import com.xiaohe66.web.code.security.po.Role;
import com.xiaohe66.web.code.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.21 10:39
 */
@XhController("/sec/role")
@Slf4j
public class RoleController extends SecurityController<RoleService, Role, RoleDto> {

    @RequiresAuthentication
    @Get("/all")
    public Result all() {
        checkRole(SEC_ADMIN_ROLE_NAME);

        List<Role> roleList = baseService.list();

        List<RoleDto> dtoList = ClassUtils.convert(RoleDto.class, roleList);

        return Result.ok(dtoList);
    }

}
