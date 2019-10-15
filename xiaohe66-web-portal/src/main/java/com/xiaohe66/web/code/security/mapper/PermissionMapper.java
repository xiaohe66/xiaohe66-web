package com.xiaohe66.web.code.security.mapper;


import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.security.po.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface PermissionMapper extends IBaseMapper<Permission> {

    Set<String> listPermissionInRoleId(@Param("roleIdList") List<Integer> roleIdList);

}
