package com.xiaohe66.web.domain.sys.sec.aggregate;

import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * @author xiaohe
 * @since 2021.10.28 17:27
 */
@Builder
@Getter
@ToString
@AllArgsConstructor
public class Role implements Aggregate<Role, RoleId> {

    @NonNull
    private final RoleId id;

    @NonNull
    private final RoleName roleName;

    @Override
    public boolean hasSameRootAttribute(Role other) {
        return other != null;
    }
}
