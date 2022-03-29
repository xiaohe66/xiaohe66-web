package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.19 12:05
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NewsBannerId extends LongId {

    public NewsBannerId(long value) {
        super(value);
    }
}
