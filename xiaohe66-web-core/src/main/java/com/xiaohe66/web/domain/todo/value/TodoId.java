package com.xiaohe66.web.domain.todo.value;

import com.xiaohe66.web.integration.domain.LongId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.12 18:01
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TodoId extends LongId {

    public TodoId(Long value) {
        super(value);
    }
}
