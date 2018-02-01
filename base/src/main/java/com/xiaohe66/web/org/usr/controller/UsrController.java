package com.xiaohe66.web.org.usr.controller;

import com.xiaohe66.web.common.annotation.*;
import com.xiaohe66.web.org.usr.po.Usr;
import com.xiaohe66.web.security.role.service.LoginService;
import com.xiaohe66.web.sys.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/usr")
public class UsrController {

    private static final String INDEX_PAGE_URL = "usr/login";

    private static final String REGISTER_PAGE_URL = "usr/register";

    @Autowired
    private LoginService loginService;

    @Page("/index")
    public String index(){
        return INDEX_PAGE_URL;
    }

    @Page("/register")
    public String register(){
        return REGISTER_PAGE_URL;
    }

    @Get("/login")
    public Result isLogin(){
        return Result.ok(loginService.isLogin());
    }

    @Post("/login")
    public Result login(String usrName, String usrPwd){
        return Result.ok(loginService.login(usrName,usrPwd));
    }

    @Del("/login")
    public  Result logout(){
        loginService.logout();
        return Result.ok(null);
    }

    @Post("/register")
    public Result register(Usr usr, String code){
        return Result.ok(loginService.register(usr,code));
    }
}
