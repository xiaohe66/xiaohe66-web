package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.security.dto.RoleDto;
import com.xiaohe66.web.code.security.po.Role;
import com.xiaohe66.web.code.security.service.RoleService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohe
 * @time 2019.10.21 10:39
 */
@XhController("/sec/role")
@Slf4j
public class RoleController extends SecurityController<RoleService, Role, RoleDto> {

    @Get("/test")
    public String test(){
        return "test";
    }

}
