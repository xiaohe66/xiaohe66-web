package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 11:07
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class WxUnionId extends StringValue {

    public WxUnionId(@NonNull String value) {
        super(value);
    }
}
