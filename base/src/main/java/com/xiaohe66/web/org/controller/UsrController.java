package com.xiaohe66.web.org.controller;

import com.xiaohe66.web.common.annotation.*;
import com.xiaohe66.web.common.util.SpringUtils;
import com.xiaohe66.web.org.po.Usr;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.security.service.LoginService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.sys.dto.CurrentUsr;
import com.xiaohe66.web.sys.dto.Result;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/usr")
public class UsrController{

    private static final String INDEX_PAGE_URL = "org/login";

    private static final String REGISTER_PAGE_URL = "org/register";

    private static final String USR_DATA_PAGE_URL = "org/usr_data";

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

    @Page("/me")
    public String me(Model model, CurrentUsr currentUsr){
        model.addAttribute("page",USR_DATA_PAGE_URL);
        return PageController.USR_ZONE_PAGE_URL;
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
