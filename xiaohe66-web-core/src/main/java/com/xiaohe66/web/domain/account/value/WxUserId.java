package com.xiaohe66.web.domain.account.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.08.11 17:47
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class WxUserId extends LongId {

    public WxUserId(long value) {
        super(value);
    }
}
