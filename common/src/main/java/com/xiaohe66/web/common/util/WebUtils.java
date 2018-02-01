package com.xiaohe66.web.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

/**
 * xh-web工具类
 *
 * @author xiaohe
 * @time 17-11-12 012
 */
public class WebUtils {

    /**
     * 获取当前用户的session
     *
     * @return
     *      当前用户的session
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }
}
