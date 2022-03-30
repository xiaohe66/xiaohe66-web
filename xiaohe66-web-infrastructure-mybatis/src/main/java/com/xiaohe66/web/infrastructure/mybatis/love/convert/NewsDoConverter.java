package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.infrastructure.domain.adapter.love.NewsConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.model.NewsDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.01 17:17
 */
@Mapper
public interface NewsDoConverter extends DoConverter<News, NewsDo>, NewsConverter {

}
