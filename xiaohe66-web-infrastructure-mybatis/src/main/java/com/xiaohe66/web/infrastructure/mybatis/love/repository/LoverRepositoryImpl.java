package com.xiaohe66.web.infrastructure.mybatis.love.repository;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.love.agg.Lover;
import com.xiaohe66.web.domain.love.repository.LoverRepository;
import com.xiaohe66.web.domain.love.value.LoverId;
import com.xiaohe66.web.infrastructure.mybatis.love.convert.LoverDoConverter;
import com.xiaohe66.web.infrastructure.mybatis.love.mapper.LoverMapper;
import com.xiaohe66.web.infrastructure.mybatis.love.model.LoverDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.29 12:07
 */
@Repository
public class LoverRepositoryImpl
        extends AbstractMybatisService<LoverDoConverter, LoverMapper, LoverDo, Lover, LoverId>
        implements LoverRepository {

    @Override
    public Lover getByAccountIdValid(AccountId accountId) {

        LoverDo loverDo = baseMapper.getByAccountIdValid(accountId.getValue());

        Lover lover = dataConverter.toAgg(loverDo);

        saveSnapshot(dataConverter.copyAgg(lover));

        return lover;
    }

    @Override
    public List<Lover> getByAccountId(AccountId accountId) {
        List<LoverDo> loverDo = baseMapper.getByAccountId(accountId.getValue());

        List<Lover> lover = dataConverter.toAgg(loverDo);

        return lover;
    }
}
