package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.domain.wx.user.value.WxUserOpenId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.07 11:43
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MessageText extends WxUserOpenId {

    public MessageText(String value) {
        super(value);
    }
}
