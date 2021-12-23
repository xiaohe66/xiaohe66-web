package com.xiaohe66.web.domain.file.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.23 21:17
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PicturePath extends StringValue {

    public PicturePath(@NonNull String value) {
        super(value);
    }
}
