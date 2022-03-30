package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.NewsBanner;
import com.xiaohe66.web.infrastructure.domain.adapter.love.NewsBannerConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.model.NewsBannerDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.19 12:26
 */
@Mapper
public interface NewsBannerDoConverter extends DoConverter<NewsBanner, NewsBannerDo>, NewsBannerConverter {

}
