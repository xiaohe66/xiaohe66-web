package com.xiaohe66.web.code.security.auth.shrio;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.data.Final;
import com.xiaohe66.web.base.data.Result;
import com.xiaohe66.web.base.util.JsonUtils;
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
public class ShiroFilterOnAccessDeniedHandler implements ShiroFilterInvocationHandler.Handler {

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

        Result ret = subject.getPrincipal() == null ?
                Result.err(CodeEnum.B2_NOT_LOGGED_IN) :
                Result.err(CodeEnum.B2_MISSING_PERMITTED);


        response.setContentType(Final.HeaderKey.JSON_UTF_8);
        response.getWriter().println(JsonUtils.toString(ret));

        return false;

    }
}
