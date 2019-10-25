package com.xiaohe66.web.controller;

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

    private static final String INDEX_PAGE_HTML = "index";

    @Resource
    private ArticleService articleService;

    @Resource
    private UserService userService;

    @Resource
    private UserFileService usrFileService;

    @Page("/about")
    public String about(Model model){
        model.addAttribute("title","关于");
        return "about";
    }

    @Page(value = {"/index",""})
    public String index(Model model){
//        model.addAttribute("list",articleService.indexArticle());
        model.addAttribute("title","乱七八糟导航");
        model.addAttribute("usrDto",userService.lookAtUser(null));
//        model.addAttribute("page",INDEX_PAGE_HTML);
        model.addAttribute("usrDivTitle","站长");
//        model.addAttribute("fileList",usrFileService.findDtoHotTop5(null));
//        model.addAttribute("hotArticle",articleService.findDtoHotTop5(null));
        return INDEX_PAGE_HTML;
    }

}
