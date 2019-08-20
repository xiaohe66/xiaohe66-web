package com.xiaohe66.web.cache;


import net.sf.ehcache.Element;

/**
 * @author xh
 * @date 18-10-18 018
 */
public class CacheHelper {

    private static XhCache xhCache;

    public static void init(XhCache xhCache){
        CacheHelper.xhCache = xhCache;
    }

    public static void put5(String key, Object value){
        xhCache.getCache5().put(new Element(key,value));
    }

    public static void remove5(String key){
        xhCache.getCache5().remove(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get5(String key){
        Element element = xhCache.getCache5().get(key);
        return element == null ? null : (T) element.getObjectValue();
    }

    public static String getStr5(String key){
        Element element = xhCache.getCache5().get(key);
        return element == null ? "" : element.getObjectValue().toString();
    }

    public static void put30(String key, Object value){
        xhCache.getCache30().put(new Element(key,value));
    }

    public static void remove30(String key){
        xhCache.getCache30().remove(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get30(String key){
        Element element = xhCache.getCache30().get(key);
        return element == null ? null : (T) element.getObjectValue();
    }

    public static String getStr30(String key){
        Element element = xhCache.getCache30().get(key);
        return element == null ? "" : element.getObjectValue().toString();
    }



}
