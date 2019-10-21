package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.security.dto.RolePermissionDto;
import com.xiaohe66.web.code.security.po.RolePermission;
import com.xiaohe66.web.code.security.service.RolePermissionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohe
 * @time 2019.10.21 11:17
 */
@XhController("/sec/role/permission")
@Slf4j
public class RolePermissionController extends SecurityController<RolePermissionService, RolePermission, RolePermissionDto> {
}
