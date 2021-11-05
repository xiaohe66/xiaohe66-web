package com.xiaohe66.web.infrastructure.shiro.handler;

import com.xiaohe66.common.dto.R;
import com.xiaohe66.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.cglib.proxy.MethodProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author xiaohe
 * @time 2021.02.25 15:09
 */
@Slf4j
public class ShiroFilterOnAccessDeniedHandler implements ShiroFilterInterceptor.Handler {

    @Override
    public Object invoke(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        HttpServletRequest request = (HttpServletRequest) args[0];
        HttpServletResponse response = (HttpServletResponse) args[1];

        String accept = request.getHeader("Accept");

        // 浏览器请求时返回页面
        if (accept != null && accept.contains("text/html")) {
            return proxy.invokeSuper(obj, args);
        }

        // 其它返回 json
        Subject subject = SecurityUtils.getSubject();


        R<Void> ret = subject.getPrincipal() == null ?
                R.err("未登录") :
                R.err("无操作权限");


        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().println(JsonUtils.toString(ret));

        return false;

    }
}
