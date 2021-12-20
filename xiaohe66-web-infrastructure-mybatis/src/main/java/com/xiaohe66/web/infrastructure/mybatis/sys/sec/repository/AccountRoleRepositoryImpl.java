package com.xiaohe66.web.infrastructure.mybatis.sys.sec.repository;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.mapper.AccountRoleMapper;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.AccountRoleDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.10.28 18:12
 */
@Repository
@Slf4j
public class AccountRoleRepositoryImpl extends ServiceImpl<AccountRoleMapper, AccountRoleDo> {

    public List<AccountRoleDo> listByAccountId(AccountId accountId) {

        AccountRoleDo query = new AccountRoleDo();
        query.setAccountId(accountId.getValue());

        QueryWrapper<AccountRoleDo> queryWrapper = new QueryWrapper<>(query);

        return list(queryWrapper);
    }

    public void removeByAccountId(AccountId accountId) {

        List<AccountRoleDo> accountRoleDos = listByAccountId(accountId);
        log.info("remove account role, id : {}, accountRole : {}", accountId.getValue(), accountRoleDos);

        AccountRoleDo accountRoleDo = new AccountRoleDo();
        accountRoleDo.setAccountId(accountId.getValue());

        remove(new QueryWrapper<>(accountRoleDo));
    }

}
