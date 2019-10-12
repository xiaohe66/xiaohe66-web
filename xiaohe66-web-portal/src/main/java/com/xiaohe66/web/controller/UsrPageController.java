package com.xiaohe66.web.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.code.org.helper.UsrHelper;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.security.service.LoginService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
@XhController("/org/usr")
public class UsrPageController {

    private static final String INDEX_PAGE_URL = "org/login";

    private static final String REGISTER_PAGE_URL = "org/register";

    private static final String USR_DATA_PAGE_URL = "org/usr_data";

    @Resource
    private UserService userService;

    @Resource
    private LoginService loginService;

    @Page("/index")
    public String index(){
        return INDEX_PAGE_URL;
    }

    @Page("/register")
    public String register(Model model){
        model.addAttribute("title","注册");
        return REGISTER_PAGE_URL;
    }

    @Page("/me")
    public String me(Model model){
        model.addAttribute("title","我的");
        model.addAttribute("page",USR_DATA_PAGE_URL);
        model.addAttribute("usrDto",userService.lookAtUsr(UsrHelper.getCurrentUsrId()));
        return OtherPageController.USR_ZONE_PAGE_URL;
    }

    @Page("/pwd")
    public String pwdPage(Model model){
        model.addAttribute("title","修改密码");
        return "org/find_pwd";
    }

    @Page("/verify/{token}")
    public String register(Model model,@PathVariable("token")String token){
        loginService.register(token);
        model.addAttribute("msg","注册成功，快去登录吧");
        return OtherPageController.MSG_AGE_URL;
    }

}
