package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.IntValue;
import com.xiaohe66.web.integration.ex.BusinessException;
import com.xiaohe66.web.integration.util.Assert;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.19 12:20
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class NewsBannerSort extends IntValue {

    private static final NewsBannerSort[] CACHE;
    private static final int CACHE_LENGTH = 10;

    static {
        CACHE = new NewsBannerSort[CACHE_LENGTH];
        for (int i = 0; i < CACHE_LENGTH; i++) {
            CACHE[i] = new NewsBannerSort(i);
        }
    }

    protected NewsBannerSort(int value) {
        super(value);
    }

    public static NewsBannerSort valueOf(Integer value) {

        Assert.notNull(value);

        if (value < 0) {
            throw new BusinessException("sort cannot be less then zero");
        }

        return value < CACHE_LENGTH ? CACHE[value] : new NewsBannerSort(value);
    }
}
