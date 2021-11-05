package com.xiaohe66.web.integration.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.05 15:05
 */
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class StringValue implements ValueObject {

    @NonNull
    @Getter
    private final String value;
}
