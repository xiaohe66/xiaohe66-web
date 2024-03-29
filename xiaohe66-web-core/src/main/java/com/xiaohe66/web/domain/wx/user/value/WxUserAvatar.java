package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.FileValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaohe
 * @since 2021.12.09 21:37
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class WxUserAvatar extends FileValue {

    public WxUserAvatar(InputStream inputStream) throws IOException {
        super(inputStream);
    }
}
