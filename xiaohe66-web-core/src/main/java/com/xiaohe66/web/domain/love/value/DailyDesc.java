package com.xiaohe66.web.domain.love.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.11.30 11:57
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DailyDesc extends StringValue {

    public DailyDesc(@NonNull String value) {
        super(value);
    }
}
