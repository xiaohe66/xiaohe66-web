package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.07 12:19
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WxUserAvatarUrl extends StringValue {

    public WxUserAvatarUrl(@NonNull String value) {
        super(value);
    }
}
