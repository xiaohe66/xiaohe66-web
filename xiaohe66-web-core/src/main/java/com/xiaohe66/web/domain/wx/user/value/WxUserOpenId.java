package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.common.util.Check;
import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.Getter;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 11:32
 */
@ToString
public class WxUserOpenId implements ValueObject {

    @Getter
    private final String value;

    public WxUserOpenId(String value) {

        Check.notEmpty(value);

        this.value = value.trim();
    }

}
