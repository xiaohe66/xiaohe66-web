package com.xiaohe66.web.domain.task.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.12 18:03
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskTitle extends StringValue {

    public TaskTitle(@NonNull String value) {
        super(value);
    }
}
