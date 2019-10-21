package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.security.dto.PermissionDto;
import com.xiaohe66.web.code.security.po.Permission;
import com.xiaohe66.web.code.security.service.PermissionService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiaohe
 * @time 2019.10.21 10:39
 */
@XhController("/sec/permission")
@Slf4j
public class PermissionController extends SecurityController<PermissionService, Permission, PermissionDto> {
}
