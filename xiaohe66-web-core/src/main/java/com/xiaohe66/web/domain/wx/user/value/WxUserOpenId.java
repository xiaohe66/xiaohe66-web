package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 11:32
 */
@ToString
public class WxUserOpenId extends StringValue {

    public WxUserOpenId(String value) {
        super(value);
    }
}
