package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.29 11:49
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoverId extends LongId {

    public LoverId(long value) {
        super(value);
    }
}
