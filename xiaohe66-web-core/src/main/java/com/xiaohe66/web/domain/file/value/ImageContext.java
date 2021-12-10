package com.xiaohe66.web.domain.file.value;

import com.xiaohe66.web.integration.domain.FileValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xiaohe
 * @since 2021.12.10 22:16
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ImageContext extends FileValue {

    public ImageContext(InputStream inputStream) throws IOException {
        super(inputStream);
    }
}
