package com.xiaohe66.web.code.sys.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.code.file.service.UserFileService;
import com.xiaohe66.web.code.org.service.UserService;
import com.xiaohe66.web.code.text.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.annotation.Resource;

/**
 * 页面跳转
 * @author xiaohe
 * @time 17-10-29 029
 */
@Controller
public class OtherPageController {

    public static final String RIGHT_PAGE_URL = "common/right";

    public static final String MSG_AGE_URL = "common/msg";

    public static final String USR_ZONE_PAGE_URL = "org/usr_zone";

    private UserService userService;

    public OtherPageController(UserService userService) {
        this.userService = userService;
    }

    @Page("/about")
    public String about(Model model){
        model.addAttribute("title","关于");
        return "about";
    }

    @Page(value = {"/index",""})
    public String index(Model model){
        model.addAttribute("title","乱七八糟导航");
        model.addAttribute("usrDto",userService.lookAtUser(null));
        model.addAttribute("usrDivTitle","站长");
        return "index";
    }

}
