package com.xiaohe66.web.domain.todo.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.12 18:08
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TodoDesc extends StringValue {

    public TodoDesc(@NonNull String value) {
        super(value);
    }
}
