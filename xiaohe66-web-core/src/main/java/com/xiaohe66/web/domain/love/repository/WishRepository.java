package com.xiaohe66.web.domain.love.repository;

import com.xiaohe66.common.util.Paging;
import com.xiaohe66.web.domain.love.agg.Wish;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.WishFinished;
import com.xiaohe66.web.domain.love.value.WishId;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2022-03-28 18:13
 */
public interface WishRepository extends Repository<Wish, WishId> {

    List<Wish> listByLoverIdDesc(LoverId loverId, WishFinished finished, Paging paging);
}
