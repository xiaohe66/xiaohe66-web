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

    /**
     * 给当前session添加参数
     * @param key       key，不能为空
     * @param value     值
     */
    public static void setSessionAttr(Object key,Object value){
        Check.notEmptyCheck(key);
        getSession().setAttribute(key,value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttr(Object key){
        Check.notEmptyCheck(key);
        return (T) getSession().getAttribute(key);
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
