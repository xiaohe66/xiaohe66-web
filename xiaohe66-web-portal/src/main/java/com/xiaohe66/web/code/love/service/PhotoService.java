package com.xiaohe66.web.code.love.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.xiaohe66.web.base.base.XhPage;
import com.xiaohe66.web.base.base.impl.BaseServiceImpl;
import com.xiaohe66.web.base.util.ClassUtils;
import com.xiaohe66.web.code.love.dto.PhotoDto;
import com.xiaohe66.web.code.love.mapper.PhotoMapper;
import com.xiaohe66.web.code.love.po.Photo;
import com.xiaohe66.web.code.love.po.PhotoDesc;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @time 2019.10.11 17:53
 */
@Service
public class PhotoService extends BaseServiceImpl<PhotoMapper,Photo> {

    private PhotoDescService photoDescService;

    public PhotoService(PhotoDescService photoDescService) {
        this.photoDescService = photoDescService;
    }

    public List<PhotoDto> queryPhoto(){
        IPage<Photo> page = this.page(new XhPage<>());
        List<Photo> photoList = page.getRecords();

        return ClassUtils.convertList(PhotoDto.class, photoList,(dto, po)->{

            // todo : po 格式转换
            List<PhotoDesc> photoDescList = photoDescService.listByPhotoId(po.getId().intValue());

            List<String> collect = photoDescList.stream().map(PhotoDesc::getDesc).collect(Collectors.toList());

            dto.setPhotoDescList(collect);

        });
    }

}
