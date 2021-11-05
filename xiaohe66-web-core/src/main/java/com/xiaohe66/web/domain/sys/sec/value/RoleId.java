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

    public RoleId(Integer value) {
        super(value);
    }
}
