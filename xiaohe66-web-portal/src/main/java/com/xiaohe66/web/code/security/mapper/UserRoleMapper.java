package com.xiaohe66.web.code.security.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.security.po.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.Set;

/**
 * 用户角色关联dao
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 16:54
 */
public interface UserRoleMapper extends IBaseMapper<UserRole> {


    void addUserRoles(@Param("userId") Integer usrId, @Param("roleIdSet") Set<Integer> roleIdSet);

}
