package com.xiaohe66.web.domain.sys.sec.value;

import com.xiaohe66.web.integration.domain.StringValue;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 17:22
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class RoleName extends StringValue {

    public RoleName(@NonNull String value) {
        super(value);
    }
}
