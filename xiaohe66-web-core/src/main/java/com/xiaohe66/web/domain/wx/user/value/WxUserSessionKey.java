package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.08 10:38
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxUserSessionKey extends StringValue {

    public WxUserSessionKey(@NonNull String value) {
        super(value);
    }
}
