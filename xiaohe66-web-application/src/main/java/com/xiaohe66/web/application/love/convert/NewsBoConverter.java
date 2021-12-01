package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.web.application.love.bo.NewsSaveBo;
import com.xiaohe66.web.application.love.result.NewsResult;
import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.domain.love.value.NewsText;
import com.xiaohe66.web.integration.domain.DataConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.01 17:29
 */
@Mapper(componentModel = "spring")
public interface NewsBoConverter extends DataConverter {

    News toAgg(Long id, NewsSaveBo bo, LoverId loverId);

    @Mapping(source = "id", target = "createTime")
    NewsResult toResult(News news);

    List<NewsResult> toResult(List<News> news);

    default NewsId newId(Long id) {
        return ifPresent(id, NewsId::new);
    }

    default NewsText newText(String text) {
        return ifPresent(text, NewsText::new);
    }

}
