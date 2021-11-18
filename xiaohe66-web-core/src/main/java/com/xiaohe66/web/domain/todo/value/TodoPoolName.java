package com.xiaohe66.web.domain.todo.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.17 15:45
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TodoPoolName extends StringValue {

    public TodoPoolName(@NonNull String value) {
        super(value);
    }
}
