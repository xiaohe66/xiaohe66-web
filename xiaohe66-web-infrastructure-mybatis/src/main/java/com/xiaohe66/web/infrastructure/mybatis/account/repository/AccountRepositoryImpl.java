package com.xiaohe66.web.infrastructure.mybatis.account.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.repository.AccountRepository;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.domain.sys.sec.aggregate.Role;
import com.xiaohe66.web.infrastructure.mybatis.account.convert.AccountDataConverter;
import com.xiaohe66.web.infrastructure.mybatis.account.mapper.AccountMapper;
import com.xiaohe66.web.infrastructure.mybatis.account.model.AccountDo;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.AccountRoleDo;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.repository.AccountRoleRepositoryImpl;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.repository.RoleRepositoryImpl;
import com.xiaohe66.web.integration.AbstractMybatisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.08.11 18:08
 */
@Repository
@RequiredArgsConstructor
@Slf4j
public class AccountRepositoryImpl
        extends AbstractMybatisService<AccountDataConverter, AccountMapper, AccountDo, Account, AccountId>
        implements AccountRepository {

    private final AccountRoleRepositoryImpl accountRoleRepository;
    private final RoleRepositoryImpl roleRepository;

    @Override
    protected void insertImpl(Account account) {
        super.insertImpl(account);

        List<AccountRoleDo> list = dataConverter.toAccountRoleList(account);
        if (!list.isEmpty()) {
            accountRoleRepository.saveBatch(list);
        }
    }

    @Override
    protected void updateImpl(Account account, Account snapshot) {
        super.updateImpl(account, snapshot);

        if (!account.isSameRoles(snapshot)) {

            accountRoleRepository.removeByAccountId(account.getId());

            List<AccountRoleDo> list = dataConverter.toAccountRoleList(account);
            if (!list.isEmpty()) {
                accountRoleRepository.saveBatch(list);
            }
        }
    }

    @Override
    protected Account getByIdImpl(AccountId id) {

        AccountDo accountDo = getById(id.getValue());

        Account account = dataConverter.toAgg(accountDo);

        List<Role> roles = roleRepository.listByAccountId(id);

        for (Role role : roles) {
            account.addRole(role.getId());
        }

        return account;
    }

    @Override
    protected void removeByIdImpl(AccountId id) {

        accountRoleRepository.removeByAccountId(id);
        removeById(id.getValue());
    }

    @Override
    public Account getByName(AccountName accountName) {

        Objects.requireNonNull(accountName);

        AccountDo accountDo = new AccountDo();
        accountDo.setName(accountName.getValue());

        QueryWrapper<AccountDo> queryWrapper = new QueryWrapper<>(accountDo);

        AccountDo dbAccount = getOne(queryWrapper);

        Account agg = dataConverter.toAgg(dbAccount);

        aggregateSnapshot.save(agg);

        return agg;
    }

    @Override
    public AccountPassword getPasswordByName(AccountName accountName) {

        LambdaQueryWrapper<AccountDo> queryWrapper = new LambdaQueryWrapper<AccountDo>()
                .select(AccountDo::getPassword)
                .eq(AccountDo::getName, accountName.getValue());

        AccountDo accountDo = getOne(queryWrapper);

        return accountDo == null ? null : new AccountPassword(accountDo.getPassword());
    }
}
