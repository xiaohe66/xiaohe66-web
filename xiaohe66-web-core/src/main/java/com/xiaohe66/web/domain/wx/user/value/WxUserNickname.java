package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.07 12:18
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class WxUserNickname extends StringValue {

    public WxUserNickname(@NonNull String value) {
        super(value);
    }
}
