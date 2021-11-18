package com.xiaohe66.web.domain.todo.value;

import com.xiaohe66.web.integration.domain.IntId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.12 18:07
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TodoPoolId extends IntId {

    public TodoPoolId(Integer value) {
        super(value);
    }
}
