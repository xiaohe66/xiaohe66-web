package com.xiaohe66.web.integration.value;

import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.08.11 16:16
 */
@ToString
@EqualsAndHashCode
public class AuthCode implements ValueObject {

    @Getter
    private final String value;

    public AuthCode(String value) {
        this.value = value;
    }
}
