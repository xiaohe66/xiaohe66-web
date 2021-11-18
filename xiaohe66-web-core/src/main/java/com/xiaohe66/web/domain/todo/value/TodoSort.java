package com.xiaohe66.web.domain.todo.value;

import com.xiaohe66.web.integration.domain.IntValue;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2021.11.12 18:10
 */
@EqualsAndHashCode(callSuper = true)
public class TodoSort extends IntValue {

    private static final TodoSort[] CONST_POOL = new TodoSort[21];

    static {
        for (int i = 0; i < CONST_POOL.length; i++) {
            CONST_POOL[i] = new TodoSort(i);
        }
    }

    public TodoSort(int value) {
        super(value);
        if (value < 0) {
            throw new IllegalArgumentException("id cannot be less than zero");
        }
    }

    public static TodoSort valueOf(int sort) {
        if (sort < CONST_POOL.length) {
            return CONST_POOL[sort];
        }
        return new TodoSort(sort);
    }
}
