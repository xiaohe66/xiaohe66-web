package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.01 17:10
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewsId extends LongId {

    public NewsId(long value) {
        super(value);
    }
}
