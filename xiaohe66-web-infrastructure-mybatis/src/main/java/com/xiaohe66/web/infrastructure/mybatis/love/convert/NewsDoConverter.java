package com.xiaohe66.web.infrastructure.mybatis.love.convert;

import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.domain.love.value.NewsText;
import com.xiaohe66.web.infrastructure.mybatis.love.model.NewsDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

/**
 * @author xiaohe
 * @since 2021.12.01 17:17
 */
@Mapper(componentModel = "spring")
public interface NewsDoConverter extends DoConverter<News, NewsDo> {

    default NewsId newId(Long id) {
        return ifPresent(id, NewsId::new);
    }

    default NewsText newText(String text) {
        return ifPresent(text, NewsText::new);
    }

}
