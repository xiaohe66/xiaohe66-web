package com.xiaohe66.web.integration.domain;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author xiaohe
 * @since 2021.08.10 10:38
 */
@EqualsAndHashCode(callSuper = true)
public class LongId extends LongValue implements Id {

    public LongId(long value) {
        super(value);

        // note : 在构建时校验有效性
        if (value < 0) {
            throw new IllegalArgumentException("id cannot be less than zero");
        }
    }

    @Override
    public Serializable getSerializable() {
        return getValue();
    }

    @Override
    public String toString() {
        return "LongId(" + getValue() + ")";
    }
}
