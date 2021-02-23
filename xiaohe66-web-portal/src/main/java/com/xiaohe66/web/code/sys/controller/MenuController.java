package com.xiaohe66.web.code.sys.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.sys.dto.MenuDto;
import com.xiaohe66.web.code.sys.po.Menu;
import com.xiaohe66.web.code.sys.service.MenuService;

/**
 * @author xiaohe
 * @time 2021.02.22 17:58
 */
@XhController("/sys/menu")
public class MenuController extends BaseController<MenuService, Menu, MenuDto> {


    @Get("/all")
    public Result all() {

        return Result.ok(baseService.all());
    }

    @Override
    protected void checkSavePermitted() {
        super.checkSavePermitted();
        checkRole(Final.Sys.SEC_ADMIN_ROLE_NAME);
    }

    @Override
    protected void checkUpdatePermitted() {
        super.checkUpdatePermitted();
        checkRole(Final.Sys.SEC_ADMIN_ROLE_NAME);
    }

    @Override
    protected void checkDeletePermitted() {
        super.checkDeletePermitted();
        checkRole(Final.Sys.SEC_ADMIN_ROLE_NAME);
    }
}
