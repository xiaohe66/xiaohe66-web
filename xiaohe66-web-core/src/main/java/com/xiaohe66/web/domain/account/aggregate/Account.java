package com.xiaohe66.web.domain.account.aggregate;

import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.domain.account.value.AccountName;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author xiaohe
 * @since 2021.08.10 15:07
 */
@RequiredArgsConstructor
@Data
public class Account implements Aggregate<AccountId> {

    private final AccountId id;
    private AccountName name;

    @Override
    public AccountId getId() {
        return id;
    }
}
