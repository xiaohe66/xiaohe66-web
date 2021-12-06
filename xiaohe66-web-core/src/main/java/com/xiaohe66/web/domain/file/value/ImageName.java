package com.xiaohe66.web.domain.file.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.02 18:26
 */
@EqualsAndHashCode(callSuper = true)
@ToString
public class ImageName extends StringValue {

    public ImageName(@NonNull String value) {
        super(value);
    }
}
