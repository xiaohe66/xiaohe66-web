package com.xiaohe66.web.sys.controller;

import com.xiaohe66.web.text.po.Article;
import com.xiaohe66.web.text.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 *
 * 页面跳转
 * @author xiaohe
 * @time 17-10-29 029
 */
@Controller
public class PageController {

    private static final String INDEX_PAGE_HTML = "index";
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/index")
    public String index(Model model){
        List<Article> list = articleService.indexList();
        model.addAttribute("list",list);
        return INDEX_PAGE_HTML;
    }

    @RequestMapping("/")
    public String index(){
        return "redirect:/index";
    }
}
