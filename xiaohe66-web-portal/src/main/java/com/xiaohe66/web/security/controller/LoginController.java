package com.xiaohe66.web.security.controller;

import com.xiaohe66.web.base.annotation.Del;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.org.dto.UsrDto;
import com.xiaohe66.web.security.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 普通登录
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 11:50
 */
@XhController("/sec/login")
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;

    @Get
    public Boolean isLogin(){
        return loginService.isLogin();
    }

    @Post
    public UsrDto login(String loginName, String usrPwd){
        return loginService.login(loginName,usrPwd);
    }

    @Del
    public  void logout(){
        LOG.info("注销登录");
        loginService.logout();
    }

    @Post("/pwd")
    public void updatePwdPrepare(String email,String code){
        loginService.updatePwdPrepare(email,code);
    }

    @Put("/pwd")
    public void updatePwd(String password,String code){
        loginService.updatePwd(password,code);
    }


}
