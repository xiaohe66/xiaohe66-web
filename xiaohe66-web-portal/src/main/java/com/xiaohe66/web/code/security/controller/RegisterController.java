package com.xiaohe66.web.code.security.controller;

import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.org.po.User;
import com.xiaohe66.web.code.security.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户注册
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 12:06
 */
@XhController("/usr/register")
public class RegisterController {

    @Autowired
    private LoginService loginService;

    @Post
    public Boolean register(User user, String code){
        loginService.registerPrepare(user,code);
        return true;
    }

}
