package com.xiaohe66.web.domain.account.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.08.10 15:08
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AccountId extends LongId {

    public AccountId(long value) {
        super(value);
    }
}
