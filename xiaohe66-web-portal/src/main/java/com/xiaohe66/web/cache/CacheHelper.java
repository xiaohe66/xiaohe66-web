package com.xiaohe66.web.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author xh
 * @date 18-10-18 018
 */
public class CacheHelper {

    private CacheHelper() {
    }

    private static Cache<String, Object> cache30 = CacheBuilder.newBuilder()
            .expireAfterAccess(30, TimeUnit.MINUTES)
            .build();

    private static Cache<String, Object> cache5 = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.MINUTES)
            .build();

    public static void put5(String key, Object value) {
        cache5.put(key, value);
    }

    public static void remove5(String key) {
        cache5.invalidate(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get5(String key) {
        Object value = cache5.getIfPresent(key);
        return value == null ? null : (T) value;
    }

    public static String getStr5(String key) {
        Object value = cache5.getIfPresent(key);
        return value == null ? "" : value.toString();
    }

    public static void put30(String key, Object value) {
        cache30.put(key, value);
    }

    public static void remove30(String key) {
        cache30.invalidate(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get30(String key) {
        Object value = cache30.getIfPresent(key);
        return value == null ? null : (T) value;
    }

    public static String getStr30(String key) {
        Object value = cache30.getIfPresent(key);
        return value == null ? "" : value.toString();
    }


}
