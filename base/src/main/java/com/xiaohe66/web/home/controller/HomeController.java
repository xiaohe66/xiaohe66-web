package com.xiaohe66.web.home.controller;

import com.xiaohe66.web.common.annotation.Page;
import com.xiaohe66.web.common.annotation.XhController;
import com.xiaohe66.web.org.service.UsrFileService;
import com.xiaohe66.web.org.service.UsrService;
import com.xiaohe66.web.sys.controller.PageController;
import com.xiaohe66.web.text.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 * @author xh
 * @date 18-03-13 013
 */
@XhController
public class HomeController {

    private static final String INDEX_PAGE_HTML = "index";

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UsrService usrService;

    @Autowired
    private UsrFileService usrFileService;

    @Page(value = {"/index",""})
    public String index(Model model){
        model.addAttribute("list",articleService.indexArticle());
        model.addAttribute("title","乱七八糟导航");
        model.addAttribute("usrDto",usrService.lookAtUsr(null));
        model.addAttribute("page",INDEX_PAGE_HTML);
        model.addAttribute("usrDivTitle","站长");
        model.addAttribute("fileList",usrFileService.findDtoHotTop5(null));
        model.addAttribute("hotArticle",articleService.findDtoHotTop5(null));
        return PageController.RIGHT_PAGE_URL;
    }
}