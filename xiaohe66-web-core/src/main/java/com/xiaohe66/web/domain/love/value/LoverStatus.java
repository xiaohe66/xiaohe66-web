package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.IntValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.29 11:54
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class LoverStatus extends IntValue {

    public static final LoverStatus READY = new LoverStatus(0);
    public static final LoverStatus NORMAL = new LoverStatus(1);
    public static final LoverStatus OVER = new LoverStatus(2);

    private LoverStatus(int value) {
        super(value);
    }

    public static LoverStatus valueOf(int value) {
        switch (value) {
            case 0:
                return READY;

            case 1:
                return NORMAL;

            case 2:
                return OVER;

            default:
                throw new IllegalArgumentException("unknown status value:" + value);
        }
    }
}
