package com.xiaohe66.web.code.love.controller;

import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.Page;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.util.Check;
import com.xiaohe66.web.code.love.dto.LovePhotoDto;
import com.xiaohe66.web.code.love.dto.PhotoDto;
import com.xiaohe66.web.code.love.po.Photo;
import com.xiaohe66.web.code.love.service.PhotoService;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.11 18:00
 */
@XhController("/love/photo")
public class PhotoController extends BaseController<PhotoService, Photo, PhotoDto> {

    @Get("/wall")
    public Result list12() {
        List<LovePhotoDto> photoDtoList = baseService.listPhoto12();
        return Result.ok(photoDtoList);
    }

    @Page("/img/{id}")
    public void showImg(HttpServletResponse response, @PathVariable("id") Integer id) throws IOException {

        Check.notEmpty(id,"id");

        response.setContentType(Final.Str.CONTENT_TYPE_IMAGE_PNG);
        baseService.showImg(response.getOutputStream(), id);
    }

    @Override
    protected void checkSelect() {
        // 重写该方法，表示不检查权限
    }

}
