package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.code.love.dto.LovePhotoDto;
import com.xiaohe66.web.code.love.dto.PhotoDto;
import com.xiaohe66.web.code.love.po.Photo;
import com.xiaohe66.web.code.love.service.PhotoService;
import org.apache.shiro.authz.annotation.RequiresGuest;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.11 18:00
 */
@XhController("/love/photo")
public class PhotoController extends BaseController<PhotoService, Photo, PhotoDto> {

    @RequiresGuest
    @Get("/wall")
    public Result list12() {
        List<LovePhotoDto> photoDtoList = baseService.listPhoto12();
        return Result.ok(photoDtoList);
    }

}
