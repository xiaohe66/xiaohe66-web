package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.Put;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.love.dto.LoverDto;
import com.xiaohe66.web.code.love.po.Lover;
import com.xiaohe66.web.code.love.service.LoverService;
import com.xiaohe66.web.code.org.helper.UserHelper;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author xiaohe
 * @time 2020.01.06 17:01
 */
@XhController("/love/lover")
public class LoverController extends BaseController<LoverService, Lover, LoverDto> {

    @Post("/ready")
    public Result ready() {

        checkLogin();

        String serialNo = baseService.ready();

        return Result.ok(serialNo);
    }

    @Post("/{serialNo}")
    public Result binding(@PathVariable String serialNo) {

        checkLogin();

        Check.notEmpty(serialNo);

        baseService.binding(serialNo);

        return Result.ok();
    }

    @Put("/confirm")
    public Result confirm() {

        checkLogin();
        checkUpdatePermitted();

        baseService.confirm();

        return Result.ok();
    }

    @Get("/info")
    public Result getLoverInfo() {

        checkLogin();
        checkSelectPermitted();

        LoverDto dto = baseService.getByUserId(UserHelper.getCurrentUsrId());

        return Result.ok(dto);
    }

}
