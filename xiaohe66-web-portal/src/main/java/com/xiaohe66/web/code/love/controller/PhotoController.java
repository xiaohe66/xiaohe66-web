package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.code.love.dto.PhotoDto;
import com.xiaohe66.web.code.love.po.Photo;
import com.xiaohe66.web.code.love.service.PhotoService;

/**
 * @author xiaohe
 * @time 2019.10.11 18:00
 */
@XhController("/love/photo")
public class PhotoController extends BaseController<PhotoService, Photo, PhotoDto> {

    @Override
    protected void convertTask(PhotoDto dto, Photo po) {
        dto.setId(1000);
    }

    @Override
    protected Class<PhotoDto> dtoClass() {
        return PhotoDto.class;
    }
}
