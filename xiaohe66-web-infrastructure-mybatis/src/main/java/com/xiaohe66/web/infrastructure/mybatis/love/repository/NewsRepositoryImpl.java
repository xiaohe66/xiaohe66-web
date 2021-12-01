package com.xiaohe66.web.infrastructure.mybatis.love.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.repository.NewsRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.infrastructure.mybatis.love.convert.NewsDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.mapper.NewsMapper;
import com.xiaohe66.web.infrastructure.mybatis.love.model.NewsDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import com.xiaohe66.web.integration.domain.Paging;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.01 17:18
 */
@Repository
public class NewsRepositoryImpl
        extends AbstractMybatisService<NewsDoConverter, NewsMapper, NewsDo, News, NewsId>
        implements NewsRepository {

    @Override
    public List<News> listByLoverId(LoverId loverId, Paging paging) {

        LambdaQueryWrapper<NewsDo> queryWrapper = new LambdaQueryWrapper<NewsDo>()
                .eq(NewsDo::getLoverId, loverId.getValue())
                .last(paging.toLimit());

        List<NewsDo> list = list(queryWrapper);

        return dataConverter.toAgg(list);
    }
}
