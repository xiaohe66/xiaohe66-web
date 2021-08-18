package com.xiaohe66.web.domain.account.aggregate;

import com.xiaohe66.web.domain.account.value.AccountId;
import com.xiaohe66.web.integration.domain.Aggregate;
import com.xiaohe66.web.domain.account.value.WxUserId;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author xiaohe
 * @since 2021.08.11 17:48
 */
@RequiredArgsConstructor
@Data
public class WxUser implements Aggregate<WxUserId> {

    private final WxUserId id;
    private final AccountId accountId;
    private String name;

    @Override
    public WxUserId getId() {
        return id;
    }
}
