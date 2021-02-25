package com.xiaohe66.web.code.security.auth.shrio;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohe
 * @time 2021.02.25 13:37
 */
public class ShiroFilterInvocationHandler implements MethodInterceptor {

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
