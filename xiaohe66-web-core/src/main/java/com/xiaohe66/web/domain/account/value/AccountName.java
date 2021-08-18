package com.xiaohe66.web.domain.account.value;

import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.08.10 15:10
 */
@ToString
@EqualsAndHashCode
public class AccountName implements ValueObject {

    @Getter
    private final String value;

    public AccountName(String value) {
        if (value == null || value.length() == 0) {
            throw new IllegalArgumentException("accountName cannot be empty");
        }
        this.value = value;
    }
}
