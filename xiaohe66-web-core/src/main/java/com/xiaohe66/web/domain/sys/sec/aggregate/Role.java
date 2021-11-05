package com.xiaohe66.web.domain.sys.sec.aggregate;

import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.integration.domain.Aggregate;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

/**
 * @author xiaohe
 * @since 2021.10.28 17:27
 */
@Data
@Setter(AccessLevel.PRIVATE)
public class Role implements Aggregate<Role, RoleId> {

    @NonNull
    private final RoleId id;

    @NonNull
    private final RoleName roleName;

    @Override
    public RoleId getId() {
        return id;
    }

    @Override
    public boolean hasDiffRoot(Role other) {
        return !isSameIdentity(other) || !roleName.equals(other.getRoleName());
    }
}
