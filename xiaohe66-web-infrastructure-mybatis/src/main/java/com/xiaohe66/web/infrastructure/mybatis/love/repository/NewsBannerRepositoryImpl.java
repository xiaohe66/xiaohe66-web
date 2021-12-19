package com.xiaohe66.web.infrastructure.mybatis.love.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaohe66.web.domain.love.agg.NewsBanner;
import com.xiaohe66.web.domain.love.repository.NewsBannerRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.domain.love.value.NewsBannerId;
import com.xiaohe66.web.infrastructure.mybatis.love.convert.NewsBannerDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.mapper.NewsBannerMapper;
import com.xiaohe66.web.infrastructure.mybatis.love.model.NewsBannerDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.12.19 12:27
 */
@Repository
public class NewsBannerRepositoryImpl
        extends AbstractMybatisService<NewsBannerDoConverter, NewsBannerMapper, NewsBannerDo, NewsBanner, NewsBannerId>
        implements NewsBannerRepository {

    @Override
    public List<NewsBanner> listByLoverIdAscSort(LoverId loverId) {

        LambdaQueryWrapper<NewsBannerDo> queryWrapper = new LambdaQueryWrapper<NewsBannerDo>()
                .eq(NewsBannerDo::getLoverId, loverId.getValue())
                .orderByAsc(NewsBannerDo::getSort);

        List<NewsBannerDo> list = list(queryWrapper);

        return dataConverter.toAgg(list);
    }
}
