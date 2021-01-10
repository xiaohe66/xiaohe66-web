package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.security.dto.UserRoleDto;
import com.xiaohe66.web.code.security.po.UserRole;
import com.xiaohe66.web.code.security.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 2019.10.21 11:19
 */
@XhController("/sec/user/role")
@Slf4j
public class UserRoleController extends SecurityController<UserRoleService, UserRole, UserRoleDto> {

    @Get("/list/{userId}")
    public Result list(@PathVariable Integer userId) {
        checkRole(SEC_ADMIN_ROLE_NAME);
        checkSelect(null);

        return Result.ok(baseService.listUserRole(userId));
    }
}
