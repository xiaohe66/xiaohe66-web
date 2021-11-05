package com.xiaohe66.web.infrastructure.shiro.handler;

import org.springframework.cglib.proxy.MethodProxy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author xiaohe
 * @time 2021.02.25 14:45
 */
public class ShiroFilterIsAccessAllowedHandler implements ShiroFilterInterceptor.Handler {

    private final List<Extend> list;

    public ShiroFilterIsAccessAllowedHandler(List<Extend> list) {
        this.list = list;
    }

    @Override
    public Object invoke(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        HttpServletRequest request = (HttpServletRequest) args[0];
        HttpServletResponse response = (HttpServletResponse) args[1];

        for (Extend handler : list) {
            handler.before(request, response);
        }

        return proxy.invokeSuper(obj, args);
    }

    public interface Extend {

        boolean before(HttpServletRequest request, HttpServletResponse response);

    }
}
