package com.xiaohe66.web.infrastructure.mybatis.love.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.common.util.Paging;
import com.xiaohe66.web.domain.love.agg.Wish;
import com.xiaohe66.web.domain.love.repository.WishRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.WishFinished;
import com.xiaohe66.web.domain.love.value.WishId;
import com.xiaohe66.web.infrastructure.mybatis.love.convert.WishDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.mapper.WishMapper;
import com.xiaohe66.web.infrastructure.mybatis.love.model.WishDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2022-03-28 18:13
 */
@Repository
@Slf4j
@RequiredArgsConstructor
public class WishRepositoryImpl
        extends AbstractMybatisService<WishDoConverter, WishMapper, WishDo, Wish, WishId>
        implements WishRepository {

    @Override
    public List<Wish> listByLoverIdDesc(LoverId loverId, WishFinished finished, Paging paging) {

        LambdaQueryWrapper<WishDo> queryWrapper = new LambdaQueryWrapper<WishDo>()
                .eq(WishDo::getLoverId, loverId.getValue())
                .eq(finished != null, WishDo::getFinished, finished)
                .orderByDesc(WishDo::getId)
                .last(paging.toLimit());

        List<WishDo> list = list(queryWrapper);

        return dataConverter.toAgg(list);
    }
}
