package com.xiaohe66.web.sys.controller;

import com.xiaohe66.web.common.annotation.Page;
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

    public static final String RIGHT_PAGE_URL = "common/right";

    public static final String USR_ZONE_PAGE_URL = "org/usr_zone";

    @Page("/about")
    public String about(){
        return "about";
    }
}
