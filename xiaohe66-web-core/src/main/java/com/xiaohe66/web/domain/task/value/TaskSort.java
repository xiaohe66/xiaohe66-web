package com.xiaohe66.web.domain.task.value;

import com.xiaohe66.web.integration.domain.IntValue;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.11.12 18:10
 */
@EqualsAndHashCode(callSuper = true)
public class TaskSort extends IntValue {

    private static final TaskSort[] CONST_POOL = new TaskSort[21];

    static {
        for (int i = 0; i < CONST_POOL.length; i++) {
            CONST_POOL[i] = new TaskSort(i);
        }
    }

    public TaskSort(int value) {
        super(value);
        if (value < 0) {
            throw new IllegalArgumentException("id cannot be less than zero");
        }
    }

    public static TaskSort valueOf(int sort) {
        if (sort < CONST_POOL.length) {
            return CONST_POOL[sort];
        }
        return new TaskSort(sort);
    }
}
