package com.xiaohe66.web.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

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


    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getRequestIP() {
        HttpServletRequest request = getRequest();
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }
}
