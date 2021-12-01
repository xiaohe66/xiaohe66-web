package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.12.01 17:10
 */

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewsText extends StringValue {

    public NewsText(@NonNull String value) {
        super(value);
    }
}
