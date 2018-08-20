package com.xiaohe66.web.security.dao;

import com.xiaohe66.web.base.base.BaseDao;
import org.apache.ibatis.annotations.Param;

/**
 * 用户角色关联dao
 *
 * @author xh
 * @version 1.0
 * @time 2018-08-20 16:54
 */
public interface UsrRoleDao extends BaseDao{


    /**
     * 给用户添加角色
     * @param usrId     用户id
     * @param roleIds   角色id数组
     */
    void addUsrRoles(@Param("usrId")Long usrId, @Param("list") Long[] roleIds);

}
