package com.xiaohe66.web.domain.love.repository;


import com.xiaohe66.web.domain.love.agg.NewsBanner;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsBannerId;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.19 12:23
 */
public interface NewsBannerRepository extends Repository<NewsBanner, NewsBannerId> {

    List<NewsBanner> listByLoverIdAscSort(LoverId loverId);

}
