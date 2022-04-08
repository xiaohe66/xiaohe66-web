package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @author xiaohe
 * @since 2022.03.28 18:21
 */
@EqualsAndHashCode(callSuper = true)
public class WishDesc extends StringValue {

    public static WishDesc EMPTY = new WishDesc("");

    public WishDesc(@NonNull String value) {
        super(value);
    }

    public static WishDesc valueOf(@NonNull String value) {
        if (value.isEmpty()) {
            return EMPTY;
        }
        return new WishDesc(value);
    }

    @Override
    public String toString() {
        return "WishDesc(" + getValue() + ")";
    }
}
