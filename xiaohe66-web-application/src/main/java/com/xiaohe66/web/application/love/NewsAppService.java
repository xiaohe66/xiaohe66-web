package com.xiaohe66.web.application.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.love.bo.NewsListBo;
import com.xiaohe66.web.application.love.bo.NewsSaveBo;
import com.xiaohe66.web.application.love.convert.NewsBoConverter;
import com.xiaohe66.web.application.love.result.NewsResult;
import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.repository.NewsRepository;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.love.service.NewsService;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.01 17:20
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NewsAppService {

    private final NewsBoConverter boConverter;
    private final NewsRepository newsRepository;
    private final LoverService loverService;
    private final NewsService newsService;

    @NeedRoles(LoverService.LOVER_ROLE_NAME)
    public R<Long> save(NewsSaveBo bo) {

        LoverId loverId = loverService.getCurrentLoverId();

        long id = IdWorker.genId();

        News news = boConverter.toAgg(id, bo, loverId);

        newsService.save(news);

        return R.ok(id);
    }

    @NeedRoles(LoverService.LOVER_ROLE_NAME)
    public R<Void> removeById(Long idValue) {

        NewsId id = new NewsId(idValue);

        newsService.removeById(id);

        return R.ok();
    }

    @NeedLogin
    public R<List<NewsResult>> listByLoverId(NewsListBo bo) {

        LoverId loverId = new LoverId(bo.getLoverId());

        List<News> news = newsRepository.listByLoverId(loverId, bo.toPaging());

        return R.ok(boConverter.toResult(news));
    }

}
