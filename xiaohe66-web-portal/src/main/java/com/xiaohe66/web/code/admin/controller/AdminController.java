package com.xiaohe66.web.code.admin.controller;

import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;

/**
 * @author xiaohe
 * @time 2020.07.15 23:22
 */
@XhController("/admin")
public class AdminController  {

    @Page(value = {"index",""})
    public String index() {
        return "/admin/menu";
    }

}
