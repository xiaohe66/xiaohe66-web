package com.xiaohe66.web.code.love.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.love.po.Photo;
import com.xiaohe66.web.code.love.service.PhotoService;

/**
 * @author xiaohe
 * @time 2019.10.11 18:00
 */
@XhController("/love/photo")
public class PhotoController extends BaseController<PhotoService> {

    @Get
    public Result query() {
        IPage<Photo> page = baseService.page(new Page<>());
        return Result.ok(page);
    }
}
