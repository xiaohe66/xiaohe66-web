package com.xiaohe66.web.application.love.convert;

import com.xiaohe66.web.application.love.bo.NewsSaveBo;
import com.xiaohe66.web.application.love.result.NewsResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.infrastructure.domain.adapter.love.NewsConverter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.01 17:29
 */
@Mapper(componentModel = "spring")
public interface NewsBoConverter extends NewsConverter {

    News toAgg(Long id, NewsSaveBo bo, AccountId createId, LoverId loverId);

    @Mapping(source = "id", target = "createTime")
    NewsResult toResult(News news);

    List<NewsResult> toResult(List<News> news);

}
