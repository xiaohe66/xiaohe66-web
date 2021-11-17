package com.xiaohe66.web.integration.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.17 12:11
 */
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class LongValue implements ValueObject {

    @Getter
    private final long value;

}
