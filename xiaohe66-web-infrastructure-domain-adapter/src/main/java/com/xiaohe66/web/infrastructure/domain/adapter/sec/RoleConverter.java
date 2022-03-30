package com.xiaohe66.web.infrastructure.domain.adapter.sec;

import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.infrastructure.domain.adapter.DataConverter;

/**
 * @author xiaohe
 * @since 2022.03.30 11:47
 */
public interface RoleConverter extends DataConverter {

    default RoleId newRoleId(Integer id) {
        return ifPresent(id, RoleId::new);
    }

    default RoleName newRoleName(String id) {
        return ifPresent(id, RoleName::new);
    }

    default Integer asId(RoleId id) {
        return ifPresent(id, RoleId::getValue);
    }

    default String asRoleName(RoleName roleName) {
        return ifPresent(roleName, RoleName::getValue);
    }
}
