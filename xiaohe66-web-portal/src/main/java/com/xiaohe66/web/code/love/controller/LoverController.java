package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Post;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.exception.sec.IllegalOperationException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.love.dto.LoverDto;
import com.xiaohe66.web.code.love.po.Lover;
import com.xiaohe66.web.code.love.service.LoverService;
import com.xiaohe66.web.code.org.helper.UserHelper;

/**
 * @author xiaohe
 * @time 2020.01.06 17:01
 */
@XhController("/love/lover")
public class LoverController extends BaseController<LoverService, Lover, LoverDto> {

    @Override
    public Result post(Lover po) {
        throw new IllegalOperationException();
    }

    @Override
    public Result put(Lover po) {
        throw new IllegalOperationException();
    }

    @Post("/save")
    public Result save(Integer loveUserId) {
        Check.notEmpty(loveUserId, "loveUserId");
        checkSavePermitted();

        baseService.save(loveUserId);

        return Result.ok();
    }

    @Get("/info")
    public Result getLoverInfo(){
        LoverDto dto = baseService.getByUserId(UserHelper.getCurrentUsrId());

        return Result.ok(dto);
    }

}
