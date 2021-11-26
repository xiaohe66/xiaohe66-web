package com.xiaohe66.web.domain.account.value;

import com.xiaohe66.common.util.PasswordUtils;
import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author xiaohe
 * @since 2021.11.02 17:35
 */
public class AccountPassword implements ValueObject {

    public static final AccountPassword EMPTY = new AccountPassword("");

    @Getter
    private final String value;

    public AccountPassword(@NonNull String value) {

        // 有些用户没有密码，因此可以为空
        if (value.length() != 0 && value.length() != PasswordUtils.PASSWORD_LEN) {
            throw new IllegalArgumentException("length must be 64");
        }

        this.value = value;
    }

    public boolean verify(String text) {
        return PasswordUtils.verify(text, value);
    }
}
