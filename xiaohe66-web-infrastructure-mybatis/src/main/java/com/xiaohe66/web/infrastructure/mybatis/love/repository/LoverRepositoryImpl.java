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

/**
 * @author xiaohe
 * @since 2021.11.29 12:07
 */
@Repository
public class LoverRepositoryImpl
        extends AbstractMybatisService<LoverDoConverter, LoverMapper, LoverDo, Lover, LoverId>
        implements LoverRepository {

    @Override
    public Lover getByAccountId(AccountId accountId) {

        LoverDo loverDo = baseMapper.getByAccountId(accountId.getValue());

        return dataConverter.toAgg(loverDo);
    }
}
