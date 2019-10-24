package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.base.util.WebUtils;
import com.xiaohe66.web.code.love.dto.LovePhotoDto;
import com.xiaohe66.web.code.love.mapper.PhotoMapper;
import com.xiaohe66.web.code.love.po.Photo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.11 17:53
 */
@Service
@Slf4j
public class PhotoService extends AbstractService<PhotoMapper, Photo> {

    public List<LovePhotoDto> listPhoto12() {
        Photo photo = new Photo();
        photo.setIsShow(true);
        QueryWrapper<Photo> photoQueryWrapper = new QueryWrapper<>(photo);

        IPage<Photo> page = this.page(12, photoQueryWrapper);
        List<Photo> photoList = page.getRecords();

        return ClassUtils.convert(LovePhotoDto.class, photoList);
    }

    @Override
    public QueryWrapper<Photo> createDefaultQueryWrapper() {
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
