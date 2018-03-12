package com.xiaohe66.web.org.dao;

import com.xiaohe66.web.common.base.BaseDao;
import com.xiaohe66.web.org.po.Usr;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface UsrDao extends BaseDao<Usr> {

    /**
     * 根据用户名查询
     * @param usrName   用户名
     * @return  Usr
     */
    Usr findByUsrName(String usrName);

    /**
     * 根据用户名和密码查询
     * @param usrName   用户名
     * @param usrPwd    密码
     * @return  Usr
     */
    Usr findByUsrNameAndPwd(String usrName, String usrPwd);

    /**
     * 给用户添加角色
     * @param usrId     用户id
     * @param roleIds   角色id数组
     */
    void addUsrRoles(@Param("usrId")Long usrId,@Param("list") Long[] roleIds);
}
