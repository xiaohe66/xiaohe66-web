package com.xiaohe66.web.code.org.mapper;

import com.xiaohe66.web.base.base.IBaseMapper;
import com.xiaohe66.web.code.org.po.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaohe
 * @time 17-10-28 028
 */
public interface UserMapper extends IBaseMapper<User> {

    /**
     * 根据用户名查询
     * @param usrName   用户名
     * @return  Usr
     */
    User findByUsrName(String usrName);

    /**
     * 根据邮箱地址查询
     * @param email   邮箱地址
     * @return  Usr
     */
    User findByEmail(String email);

    /**
     * 根据用户名和密码查询
     * @param usrName   用户名
     * @param usrPwd    密码
     * @return  Usr
     */
    User findByUsrNameAndPwd(String usrName, String usrPwd);


    /**
     * 判断邮箱是否已存在
     * @param email   邮箱地址
     * @return  返回true表示已存在，返回false则不存在
     */
    boolean isExistEmail(String email);

    /**
     * 给用户添加角色
     * @param usrId     用户id
     * @param roleIds   角色id数组
     */
    void addUsrRoles(@Param("usrId") Long usrId, @Param("list") Long[] roleIds);
}
