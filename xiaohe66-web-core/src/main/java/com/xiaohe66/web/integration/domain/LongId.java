package com.xiaohe66.web.integration.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

/**
 * @author xiaohe
 * @since 2021.08.10 10:38
 */
@ToString
@EqualsAndHashCode
public class LongId implements Id {

    /**
     * 值不可更改，取值范围是0-Max，其中0用于表示还未登记的实体
     */
    @Getter
    private final Long value;

    public LongId(Long value) {
        Objects.requireNonNull(value);

        // note : 在构建时校验有效性
        if (value < 0) {
            throw new IllegalArgumentException("id cannot be less than zero");
        }
        this.value = value;
    }

}
