package com.xiaohe66.web.domain.task.value;

import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author xiaohe
 * @since 2021.12.27 22:09
 */
@EqualsAndHashCode
public class TaskChangeTime implements ValueObject {

    @Getter
    private final LocalDateTime value;

    public TaskChangeTime(LocalDateTime value) {
        this.value = value;
    }
}
