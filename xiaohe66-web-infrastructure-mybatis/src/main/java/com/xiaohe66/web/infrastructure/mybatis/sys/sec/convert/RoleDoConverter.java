package com.xiaohe66.web.infrastructure.mybatis.sys.sec.convert;

import com.xiaohe66.web.domain.sys.sec.aggregate.Role;
import com.xiaohe66.web.infrastructure.domain.adapter.sec.RoleConverter;
import com.xiaohe66.web.infrastructure.mybatis.sys.sec.model.RoleDo;
import com.xiaohe66.web.integration.DoConverter;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.11.01 16:59
 */
@Mapper
public interface RoleDoConverter extends DoConverter<Role, RoleDo>, RoleConverter {

    List<Role> toEntity(List<RoleDo> roleDo);

}
