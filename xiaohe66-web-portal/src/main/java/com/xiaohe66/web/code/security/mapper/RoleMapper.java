package com.xiaohe66.web.code.security.mapper;


import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.security.po.Role;

import java.util.List;
import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface RoleMapper extends IBaseMapper<Role> {

    Set<Integer> listDefaultRoleId();

    Set<String> listRoleNameByUsrId(Integer userId);

    List<Role> listRoleByUserId(Integer userId);

    List<Role> listRoleByUserName(String userName);
}
