package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.security.dto.UserRoleDto;
import com.xiaohe66.web.code.security.po.UserRole;
import com.xiaohe66.web.code.security.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;

/**
 * @author xiaohe
 * @time 2019.10.21 11:19
 */
@XhController("/sec/user/role")
@Slf4j
public class UserRoleController extends SecurityController<UserRoleService, UserRole, UserRoleDto> {

    @Get("/user/{userId}")
    public Result list(@PathVariable Integer userId) {
        checkRole(SEC_ADMIN_ROLE_NAME);

        return Result.ok(baseService.listRoleByUserId(userId));
    }

    @RequiresAuthentication
    @Put("/user")
    public Result update(Integer userId,
                         @RequestParam(value = "ids[]", required = false) int[] roleIdArr) {

        checkRole(SEC_ADMIN_ROLE_NAME);

        if (log.isDebugEnabled()) {
            log.debug("update user role, userId : {}, roleIdArr : {}", userId, Arrays.toString(roleIdArr));
        }

        return baseService.updateByUserId(userId, roleIdArr == null ? ArrayUtils.EMPTY_INT_ARRAY : roleIdArr);
    }


}
