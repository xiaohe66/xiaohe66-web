package com.xiaohe66.web.domain.sys.sec.value;

import com.xiaohe66.web.integration.domain.IntId;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 17:23
 */
@ToString
@EqualsAndHashCode(callSuper = true)
public class RoleId extends IntId {

    public static final RoleId ADMIN_ROLE_ID = new RoleId(1);
    public static final RoleId WX_ROLE_ID = new RoleId(2);
    public static final RoleId LOVE_ROLE_ID = new RoleId(3);
    public static final RoleId TASK_ROLE_ID = new RoleId(4);
    public static final RoleId LOVER_ROLE_ID = new RoleId(5);

    public static final long ADMIN_ROLE_VALUE = 1;
    public static final long WX_ROLE_VALUE = 2;
    public static final long LOVE_ROLE_VALUE = 3;
    public static final long TASK_ROLE_VALUE = 4;
    public static final long LOVER_ROLE_VALUE = 5;

    public RoleId(int value) {
        super(value);
    }
}
