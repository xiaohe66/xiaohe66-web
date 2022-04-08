package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @author xiaohe
 * @since 2022.03.28 18:22
 */
@EqualsAndHashCode(callSuper = true)
public class WishRemark extends StringValue {

    public static final WishRemark EMPTY = new WishRemark("");

    public WishRemark(@NonNull String value) {
        super(value);
    }

    public static WishRemark valueOf(@NonNull String value) {
        if (value.isEmpty()) {
            return EMPTY;
        }
        return new WishRemark(value);
    }

    @Override
    public String toString() {
        return "WishRemark(" + getValue() + ")";
    }
}
