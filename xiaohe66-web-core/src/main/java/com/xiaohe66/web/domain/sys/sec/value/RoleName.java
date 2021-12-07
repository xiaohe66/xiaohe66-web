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

    public static final String ADMIN_ROLE_VALUE = "admin";
    public static final String WX_ROLE_VALUE = "wx";
    public static final String LOVE_ROLE_VALUE = "love";
    public static final String TASK_ROLE_VALUE = "task";
    public static final String LOVER_ROLE_VALUE = "lover";

    public RoleName(@NonNull String value) {
        super(value);
    }
}
