package com.xiaohe66.web.infrastructure.mybatis.account.convert;

import com.xiaohe66.common.util.IdWorker;
import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.infrastructure.domain.adapter.account.AccountConverter;
import com.xiaohe66.web.infrastructure.mybatis.account.model.AccountDo;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.AccountRoleDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiaohe
 * @since 2021.08.12 11:02
 */
@Mapper
public interface AccountDoConverter extends DoConverter<Account, AccountDo>, AccountConverter {

    default List<AccountRoleDo> toAccountRoleList(Account account) {

        Long accountIdValue = account.getId().getValue();

        return account.roleIdsStream()
                .map(roleId -> {
                    AccountRoleDo accountRoleDo = new AccountRoleDo();
                    accountRoleDo.setId(IdWorker.genId());
                    accountRoleDo.setAccountId(accountIdValue);
                    accountRoleDo.setRoleId(roleId.getValue());
                    return accountRoleDo;
                })
                .collect(Collectors.toList());
    }
}
