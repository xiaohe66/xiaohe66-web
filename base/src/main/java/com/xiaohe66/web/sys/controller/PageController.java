package com.xiaohe66.web.sys.controller;

import com.xiaohe66.web.common.annotation.Page;
import org.springframework.stereotype.Controller;

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
