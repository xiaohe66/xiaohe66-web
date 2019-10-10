package com.xiaohe66.web.code.security.dao;


import com.xiaohe66.web.base.base.BaseDao;
import com.xiaohe66.web.code.security.po.Role;

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
    Set<String> findRoleNameByUsrId(Long usrId);
}
