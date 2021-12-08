package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.IntValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.08 10:57
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxUserSex extends IntValue {

    public static final WxUserSex UNKNOWN = new WxUserSex(0);
    public static final WxUserSex BOY = new WxUserSex(1);
    public static final WxUserSex GIRL = new WxUserSex(2);

    protected WxUserSex(int value) {
        super(value);
    }

    public static WxUserSex valueOf(Integer value) {
        if (value == null) {
            return UNKNOWN;
        }
        switch (value) {
            case 1:
                return BOY;
            case 2:
                return GIRL;
            default:
                return UNKNOWN;
        }
    }
}