package com.xiaohe66.web.domain.wx.user.value;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 14:14
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class WxTodoUserOpenId extends WxUserOpenId {

    public WxTodoUserOpenId(String value) {
        super(value);
    }
}
