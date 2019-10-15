package com.xiaohe66.web.code.love.service;

import com.xiaohe66.web.base.base.impl.AbstractService;
import com.xiaohe66.web.code.love.mapper.PhotoDescMapper;
import com.xiaohe66.web.code.love.po.PhotoDesc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @time 2019.10.11 18:22
 */
@Service
@Slf4j
public class PhotoDescService extends AbstractService<PhotoDescMapper, PhotoDesc> {

    public List<PhotoDesc> listByPhotoId(Integer photoId) {
        return baseMapper.listByPhotoId(photoId);
    }
}
