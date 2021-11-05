package com.xiaohe66.web.infrastructure.shiro.handler;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 用于对 shiro 的 Filter 进行增强，使每个 Filter 调用前都执行本类的方法
 *
 * @author xiaohe
 * @time 2021.02.25 13:37
 */
public class ShiroFilterInterceptor implements MethodInterceptor {

    private Map<String, Handler> extendMap = new HashMap<>();

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

        String name = method.getName();
        for (Map.Entry<String, Handler> entry : extendMap.entrySet()) {
            if (name.equals(entry.getKey())) {
                return entry.getValue().invoke(obj, method, args, proxy);
            }
        }

        return proxy.invokeSuper(obj, args);
    }

    public void addExtend(String methodName, Handler extendList) {
        extendMap.put(methodName, extendList);
    }

    public interface Handler {

        Object invoke(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable;
    }
}
