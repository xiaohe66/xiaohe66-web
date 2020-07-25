package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.MsgException;
import com.xiaohe66.web.base.exception.param.MissingParamException;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.security.service.LoginService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

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
    public Result isLogin() {
        return Result.ok(loginService.isLogin());
    }

    @Post
    public Result login(String loginName, String userPwd) {
        return Result.ok(loginService.login(loginName, userPwd));
    }

    @RequiresAuthentication
    @Del
    public void logout() {
        loginService.logout();
    }

    //    @Page("/findPwd")
    public String findPwdPage(Model model) {
        model.addAttribute("title", "修改密码");
        return "org/find_pwd";
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

    // 用于注册发邮件
//    @Post("/register")
    public Boolean register(User user, String code) {
        try {
            loginService.registerPrepare(user, code);
        } catch (MissingParamException e) {
            throw new MsgException(e.getCode(), e.getMessage());
        }
        return true;
    }

    // 用于注册验证
//    @Page("/register/{token}")
    public String registerVerify(@PathVariable String token) {
        loginService.register(token);
        return "redirect:/";
    }


}
