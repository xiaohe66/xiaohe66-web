package com.xiaohe66.web.domain.love.service;

import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.repository.NewsRepository;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.ex.ErrorCodeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiaohe
 * @since 2021.12.01 17:23
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class NewsService {

    private final NewsRepository newsRepository;
    private final LoverService loverService;

    public void save(News news) {
        newsRepository.save(news);
    }

    public void removeById(NewsId id) {

        News news = newsRepository.getById(id);

        if (news == null) {
            throw new BusinessException(ErrorCodeEnum.NOT_FOUND);
        }

        loverService.checkLoverPermission(news.getLoverId());

        newsRepository.removeById(id);
    }

}
