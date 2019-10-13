package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.love.dto.LovePhotoDto;
import com.xiaohe66.web.code.love.mapper.PhotoMapper;
import com.xiaohe66.web.code.love.po.Photo;
import com.xiaohe66.web.code.love.po.PhotoDesc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @time 2019.10.11 17:53
 */
@Service
@Slf4j
public class PhotoService extends AbstractService<PhotoMapper, Photo> {

    private PhotoDescService photoDescService;

    public PhotoService(PhotoDescService photoDescService) {
        this.photoDescService = photoDescService;
    }

    public List<LovePhotoDto> listPhoto12() {

        IPage<Photo> page = this.page(12);
        List<Photo> photoList = page.getRecords();

        return ClassUtils.convert(LovePhotoDto.class, photoList, (dto, po) -> {

            List<PhotoDesc> photoDescList = photoDescService.listByPhotoId(po.getId());

            List<String> collect = photoDescList.stream().map(PhotoDesc::getDesc).collect(Collectors.toList());

            dto.setPhotoDescList(collect);

        });
    }

}
