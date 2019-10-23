package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.org.dto.UserDto;
import com.xiaohe66.web.code.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 普通登录
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 11:50
 */
@XhController("/sec/login")
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    private LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Get
    public Boolean isLogin() {
        return loginService.isLogin();
    }

    @Post
    public UserDto login(String loginName, String userPwd) {
        return loginService.login(loginName, userPwd);
    }

    @Del
    public void logout() {
        loginService.logout();
    }

    // 用于邮箱找回密码
    @Post("/pwd")
    public void updatePwdPrepare(String email, String code) {
        loginService.updatePwdPrepare(email, code);
    }

    @Put("/pwd")
    public void updatePwd(String password, String code) {
        loginService.updatePwd(password, code);
    }


}
