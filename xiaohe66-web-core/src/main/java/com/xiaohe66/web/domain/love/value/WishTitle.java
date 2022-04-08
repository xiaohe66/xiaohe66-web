package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @author xiaohe
 * @since 2022.03.28 18:16
 */
@EqualsAndHashCode(callSuper = true)
public class WishTitle extends StringValue {

    public WishTitle(@NonNull String value) {
        super(value);
        if (value.isEmpty()) {
            throw new IllegalArgumentException("WishTitle cannot be empty");
        }
    }

    public static WishTitle valueOf(@NonNull String value) {
        return new WishTitle(value);
    }

    @Override
    public String toString() {
        return "WishTitle(" + getValue() + ")";
    }
}
