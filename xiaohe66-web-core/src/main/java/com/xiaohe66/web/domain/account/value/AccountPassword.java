package com.xiaohe66.web.domain.account.value;

import com.xiaohe66.common.util.PasswordUtils;
import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.Getter;

/**
 * @author xiaohe
 * @since 2021.11.02 17:35
 */
public class AccountPassword implements ValueObject {

    @Getter
    private final String value;

    public AccountPassword(String value) {

        if (value == null || value.length() != PasswordUtils.PASSWORD_LEN) {
            throw new IllegalArgumentException("length must be 64");
        }

        this.value = value;
    }

    public boolean verify(String text) {
        return PasswordUtils.verify(text, value);
    }
}
