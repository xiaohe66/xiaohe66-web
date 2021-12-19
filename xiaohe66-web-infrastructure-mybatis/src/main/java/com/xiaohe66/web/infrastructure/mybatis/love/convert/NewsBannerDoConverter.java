package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.file.value.ImageId;
import com.xiaohe66.web.domain.love.agg.NewsBanner;
import com.xiaohe66.web.domain.love.value.NewsBannerId;
import com.xiaohe66.web.domain.love.value.NewsBannerSort;
import com.xiaohe66.web.infrastructure.mybatis.love.model.NewsBannerDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.19 12:26
 */
@Mapper(componentModel = "spring")
public interface NewsBannerDoConverter extends DoConverter<NewsBanner, NewsBannerDo> {

    default ImageId newImageId(Long id){
        return ifPresent(id, ImageId::new);
    }

    default NewsBannerId newsId(Long id) {
        return ifPresent(id, NewsBannerId::new);
    }

    default NewsBannerSort newsId(Integer sort) {
        return ifPresent(sort, NewsBannerSort::valueOf);
    }

}
