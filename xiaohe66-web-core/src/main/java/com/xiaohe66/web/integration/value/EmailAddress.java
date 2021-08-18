package com.xiaohe66.web.integration.value;

import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.08.11 15:19
 */
@ToString
@EqualsAndHashCode
public class EmailAddress implements ValueObject {

    private static final String EMAIL_REGEX = "^[a-z0-9A-Z]+[-|a-z0-9A-Z._]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$";

    @Getter
    private final String value;

    public EmailAddress(String value) {
        if (value == null || !value.matches(EMAIL_REGEX)) {
            throw new IllegalArgumentException("The email address is incorrect:" + value);
        }
        this.value = value;
    }

}
