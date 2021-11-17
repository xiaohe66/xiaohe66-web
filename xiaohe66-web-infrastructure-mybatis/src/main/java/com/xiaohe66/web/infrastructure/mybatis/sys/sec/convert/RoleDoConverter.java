package com.xiaohe66.web.infrastructure.mybatis.sys.sec.convert;

import com.xiaohe66.web.domain.sys.sec.aggregate.Role;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.domain.sys.sec.value.RoleName;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.RoleDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.01 16:59
 */
@Mapper(componentModel = "spring")
public interface RoleDoConverter extends DoConverter<Role, RoleDo> {

    List<Role> toEntity(List<RoleDo> roleDo);

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
