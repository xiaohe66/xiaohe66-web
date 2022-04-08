package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.DateValue;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * @author xiaohe
 * @since 2022.03.28 18:26
 */
@EqualsAndHashCode(callSuper = true)
public class WishFinishDate extends DateValue {

    public WishFinishDate(LocalDate value) {
        super(value);
    }

    public static WishFinishDate now() {
        return new WishFinishDate(LocalDate.now());
    }

    @Override
    public String toString() {
        return "WishFinishDate(" + getValue() + ")";
    }
}
