package com.xiaohe66.web.integration.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 11:22
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class MobileNo extends StringValue {

    public MobileNo(@NonNull String value) {
        super(value);
    }
}
