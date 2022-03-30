package com.xiaohe66.web.infrastructure.mybatis.love.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.love.agg.Daily;
import com.xiaohe66.web.domain.love.repository.DailyRepository;
import com.xiaohe66.web.domain.love.value.DailyId;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.infrastructure.mybatis.love.convert.DailyDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.mapper.DailyMapper;
import com.xiaohe66.web.infrastructure.mybatis.love.model.DailyDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import com.xiaohe66.common.util.Paging;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.30 12:14
 */
@Repository
public class DailyRepositoryImpl
        extends AbstractMybatisService<DailyDoConverter, DailyMapper, DailyDo, Daily, DailyId>
        implements DailyRepository {

    @Override
    public List<Daily> listByLoverIdDescId(LoverId loverId, Paging paging) {

        LambdaQueryWrapper<DailyDo> queryWrapper = new LambdaQueryWrapper<DailyDo>()
                .eq(DailyDo::getLoverId, loverId.getValue())
                .orderByDesc(DailyDo::getId)
                .last(paging.toLimit());

        List<DailyDo> list = list(queryWrapper);

        return dataConverter.toAgg(list);
    }
}
