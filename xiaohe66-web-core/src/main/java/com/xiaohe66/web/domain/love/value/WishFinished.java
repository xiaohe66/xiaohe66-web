package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.BooleanValue;
import lombok.EqualsAndHashCode;

/**
 * @author xiaohe
 * @since 2022.03.28 18:19
 */
@EqualsAndHashCode(callSuper = true)
public class WishFinished extends BooleanValue {

    public static final WishFinished TRUE = new WishFinished(true);
    public static final WishFinished FALSE = new WishFinished(false);

    private WishFinished(boolean value) {
        super(value);
    }

    public static WishFinished valueOf(boolean value) {
        return value ? TRUE : FALSE;
    }

    public static WishFinished valueOf(Boolean value) {
        if (value == null) {
            return null;
        }
        return value ? TRUE : FALSE;
    }

    @Override
    public String toString() {
        return "WishFinished(" + getValue() + ")";
    }
}
