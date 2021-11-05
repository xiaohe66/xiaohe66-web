package com.xiaohe66.web.domain.sys.sec.value;

import com.xiaohe66.web.integration.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

/**
 * @author xiaohe
 * @since 2021.11.05 11:18
 */
@AllArgsConstructor
@EqualsAndHashCode
public class PermissionName implements ValueObject {

    @NonNull
    @Getter
    private final String value;

}
