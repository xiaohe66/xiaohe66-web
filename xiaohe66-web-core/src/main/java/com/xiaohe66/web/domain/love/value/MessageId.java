package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.07 11:43
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MessageId extends LongId {

    public MessageId(Long value) {
        super(value);
    }
}
