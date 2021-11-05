package com.xiaohe66.web.domain.sys.sec.repository;

import com.xiaohe66.web.domain.sys.sec.aggregate.Role;
import com.xiaohe66.web.domain.sys.sec.value.RoleId;
import com.xiaohe66.web.integration.domain.Repository;

import java.util.List;

/**
 * @author xiaohe
 * @since 2021.10.28 18:10
 */
public interface RoleRepository extends Repository<Role, RoleId> {

    List<Role> listDefaultRole();

}
