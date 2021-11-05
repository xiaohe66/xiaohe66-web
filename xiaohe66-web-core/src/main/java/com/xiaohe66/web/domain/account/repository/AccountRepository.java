package com.xiaohe66.web.domain.account.repository;

import com.xiaohe66.web.domain.account.aggregate.Account;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import com.xiaohe66.web.domain.account.value.AccountPassword;
import com.xiaohe66.web.integration.domain.Repository;

/**
 * 账号数据仓库
 *
 * @author xiaohe
 * @since 2021.08.10 15:14
 */
public interface AccountRepository extends Repository<Account, AccountId> {

    Account getByName(AccountName accountName);

    AccountPassword getPasswordByName(AccountName accountName);

}
