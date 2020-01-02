package com.xiaohe66.web.test.base.copyprop.copy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaohe
 * @time 2020.01.02 10:12
 */
public class SelfReflectCopyService extends AbstractCopyService {
    private static Map<Class, Method[]> methodArrMap = new HashMap<>();

    @Override
    public void copy(Object dest, Object orig) throws InvocationTargetException, IllegalAccessException {
        Method[] destMethods = getMethods(dest);
        Method[] origMethods = getMethods(orig);
        Map<String, Method> methodMap = new HashMap<>();
        for (Method method : origMethods) {
            if (!method.getName().startsWith("get")) {
                continue;
            }
            methodMap.put(method.getName(), method);
        }
        for (Method method : destMethods) {
            if (!method.getName().startsWith("set")) {
                continue;
            }
            Method method1 = methodMap.get(method.getName().replaceAll("set", "get"));
            Object value = method1.invoke(orig);
            method.invoke(dest, value);
        }
    }

    private static Method[] getMethods(Object obj) {
        Method[] methods = methodArrMap.get(obj.getClass());
        if (null == methods) {
            methods = obj.getClass().getDeclaredMethods();
            methodArrMap.put(obj.getClass(), methods);
        }
        return methods;
    }
}
