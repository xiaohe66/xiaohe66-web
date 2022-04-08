package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2022-03-28 18:13
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WishId extends LongId {

    public WishId(long value) {
        super(value);
    }
}
