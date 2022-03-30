package com.xiaohe66.web.domain.love.repository;

import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.common.util.Paging;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.30 12:10
 */
public interface DailyRepository extends Repository<Daily, DailyId> {

    List<Daily> listByLoverIdDescId(LoverId loverId, Paging paging);

}
