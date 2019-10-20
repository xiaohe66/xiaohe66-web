package com.xiaohe66.web.code.love.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.base.annotation.Get;
import com.xiaohe66.web.base.annotation.XhController;
import com.xiaohe66.web.base.base.BaseController;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.love.dto.LovePhotoDto;
import com.xiaohe66.web.code.love.dto.PhotoDto;
import com.xiaohe66.web.code.love.po.Photo;
import com.xiaohe66.web.code.love.service.PhotoService;

import javax.servlet.http.HttpServletRequest;
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

    @Override
    protected void checkSelectPermitted() {
        // 重写该方法，表示不检查权限
    }

    @Override
    protected Wrapper<Photo> createPageQueryWrapper() {
        HttpServletRequest request = WebUtils.getRequest();
        String name = request.getParameter("name");
        if (name != null) {
            String nameStr = name.trim();
            if (nameStr.length() != 0) {
                Photo photo = new Photo();
                QueryWrapper<Photo> queryWrapper = new QueryWrapper<>(photo);
                // todo : 创建工具类，由工具类统一创建模糊查询参数
                queryWrapper.like("name", "%" + nameStr + "%");
                return queryWrapper;
            }
        }
        return null;
    }
}
