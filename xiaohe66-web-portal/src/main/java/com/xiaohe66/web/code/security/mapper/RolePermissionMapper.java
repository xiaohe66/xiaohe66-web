package com.xiaohe66.web.code.security.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.security.po.RolePermission;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * @author xiaohe
 * @time 2019.10.21 11:13
 */
public interface RolePermissionMapper extends IBaseMapper<RolePermission> {

    Set<Integer> listPermissionIdInRoleId(@Param("roleId") Integer roleId);

}
