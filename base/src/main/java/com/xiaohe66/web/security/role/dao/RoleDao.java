package com.xiaohe66.web.security.role.dao;


import com.xiaohe66.web.common.base.BaseDao;
import com.xiaohe66.web.security.role.po.Role;

import java.util.Set;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface RoleDao extends BaseDao<Role> {
    /**
     * 根据用户id查询角色名
     * @param usrId 角色名
     * @return Set<String>
     */
    Set<String> searchRoleNameByUsrId(Long usrId);
}
