package com.xiaohe66.web.infrastructure.mybatis.account.repository;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.infrastructure.mybatis.account.convert.AccountDataConverter;
import com.xiaohe66.web.infrastructure.mybatis.account.mapper.AccountMapper;
import com.xiaohe66.web.infrastructure.mybatis.account.model.AccountDo;
import com.xiaohe66.web.integration.AbstractMybatisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

/**
 * @author xiaohe
 * @since 2021.08.11 18:08
 */
@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl extends AbstractMybatisService<AccountDataConverter, AccountMapper, AccountDo> implements AccountRepository {

    @Override
    public Account findById(AccountId id) {
        AccountDo accountDo = getById(id.getValue());

        return dataConverter.toEntity(accountDo);
    }

    @Override
    public void save(Account aggregate) {

        // todo : 怎么区分是插入还是删除？
        AccountDo accountDo = dataConverter.toDo(aggregate);
        save(accountDo);
    }

    @Override
    public void removeById(AccountId id) {

        removeById(id.getValue());
    }
}
