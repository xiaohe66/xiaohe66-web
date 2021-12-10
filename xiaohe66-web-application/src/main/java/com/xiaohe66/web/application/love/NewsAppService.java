package com.xiaohe66.web.application.love;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.application.aop.annotation.NeedLogin;
import com.xiaohe66.web.application.aop.annotation.NeedRoles;
import com.xiaohe66.web.application.love.bo.NewsListBo;
import com.xiaohe66.web.application.love.bo.NewsSaveBo;
import com.xiaohe66.web.application.love.convert.NewsBoConverter;
import com.xiaohe66.web.application.love.result.NewsResult;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.repository.NewsRepository;
import com.xiaohe66.web.domain.love.service.LoverService;
import com.xiaohe66.web.domain.love.service.NewsService;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.domain.sys.sec.service.SecurityService;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
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
    private final SecurityService securityService;

    @NeedRoles(RoleName.LOVER_ROLE_VALUE)
    public R<NewsResult> save(NewsSaveBo bo) {

        AccountId currentAccountId = securityService.getCurrentAccountId();
        LoverId loverId = loverService.getCurrentLoverId();

        News news = boConverter.toAgg(IdWorker.genId(), bo, currentAccountId, loverId);

        newsService.save(news);

        return R.ok(boConverter.toResult(news));
    }

    @NeedRoles(RoleName.LOVER_ROLE_VALUE)
    public R<Void> removeById(Long idValue) {

        NewsId id = new NewsId(idValue);

        newsService.removeById(id);

        return R.ok();
    }

    @NeedLogin
    public R<List<NewsResult>> listByLoverIdDesc(NewsListBo bo) {

        LoverId loverId = new LoverId(bo.getLoverId());

        List<News> news = newsRepository.listByLoverIdDesc(loverId, bo.toPaging());

        return R.ok(boConverter.toResult(news));
    }

}
