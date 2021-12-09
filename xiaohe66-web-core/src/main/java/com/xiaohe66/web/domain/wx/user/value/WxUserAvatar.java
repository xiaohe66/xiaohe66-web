package com.xiaohe66.web.domain.wx.user.value;

import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author xiaohe
 * @since 2021.12.09 21:37
 */
@EqualsAndHashCode
@ToString
public class WxUserAvatar implements ValueObject {

    private final byte[] bytes;

    public WxUserAvatar(InputStream inputStream) throws IOException {
        // TODO : 处理大文件问题
        this.bytes = inputStream.readAllBytes();
    }

    public void write(OutputStream outputStream) throws IOException {
        outputStream.write(bytes);
    }
}
