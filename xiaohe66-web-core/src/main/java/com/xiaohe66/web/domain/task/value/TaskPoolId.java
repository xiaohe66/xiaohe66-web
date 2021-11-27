package com.xiaohe66.web.domain.task.value;

import com.xiaohe66.web.integration.domain.IntId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.12 18:07
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskPoolId extends IntId {

    public TaskPoolId(Integer value) {
        super(value);
    }
}
