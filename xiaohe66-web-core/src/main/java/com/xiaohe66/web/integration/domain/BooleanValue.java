package com.xiaohe66.web.integration.domain;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2022.03.28 18:18
 */
@RequiredArgsConstructor
@ToString
public class BooleanValue implements ValueObject {

    private final boolean value;

    public boolean getValue() {
        return value;
    }

}
