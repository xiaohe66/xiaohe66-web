package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.30 11:53
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DailyId extends LongId {

    public DailyId(long value) {
        super(value);
    }
}
