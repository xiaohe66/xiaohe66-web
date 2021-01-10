package com.xiaohe66.web.code.admin.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.admin.dto.PermissionRoleDto;
import com.xiaohe66.web.code.admin.dto.PermissionUserDto;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.security.po.Role;
import com.xiaohe66.web.code.security.service.RoleService;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @author xiaohe
 * @time 2020.07.26 18:30
 */
@XhController("/admin/perm")
public class PermissionPageController {

    private UserService userService;
    private RoleService roleService;

    public PermissionPageController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Page("/index")
    public String index(Model model) {

        List<User> userList = userService.list();
        List<PermissionUserDto> userDtoList = ClassUtils.convert(PermissionUserDto.class, userList);

        List<Role> roleList = roleService.list();
        List<PermissionRoleDto> roleDtoList = ClassUtils.convert(PermissionRoleDto.class, roleList);

        model.addAttribute("userList", userDtoList);
        model.addAttribute("roleList", roleDtoList);

        return "/admin/sys/permission.html";
    }

}
