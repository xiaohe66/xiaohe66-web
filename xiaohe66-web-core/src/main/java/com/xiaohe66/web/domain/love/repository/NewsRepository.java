package com.xiaohe66.web.domain.love.repository;

import com.xiaohe66.web.domain.love.agg.News;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsId;
import com.xiaohe66.web.integration.domain.Paging;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.01 17:15
 */
public interface NewsRepository extends Repository<News, NewsId> {

    List<News> listByLoverId(LoverId loverId, Paging paging);

}
