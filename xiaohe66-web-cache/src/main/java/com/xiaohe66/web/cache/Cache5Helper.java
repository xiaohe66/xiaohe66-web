package com.xiaohe66.web.cache;


import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * @author xh
 * @date 18-10-18 018
 */
public class Cache5Helper {

    private static final String FIVE_MINUTES_CACHE = "five_minutes_cache";

    private static final String CFG_XML_PATH = "cache/ehcache.xml";

    private static Cache cache;

    public static void init(){
        CacheManager cacheManager = CacheManager.create(Cache5Helper.class.getClassLoader().getResourceAsStream(CFG_XML_PATH));
        cache = cacheManager.getCache(FIVE_MINUTES_CACHE);
    }

    public static void put(String key, Object value){
        cache.put(new Element(key,value));
    }

    public static void remove(String key){
        cache.remove(key);
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key){
        Element element = cache.get(key);
        return element == null ? null : (T) element.getObjectValue();
    }

    public static String getStr(String key){
        Element element = cache.get(key);
        return element == null ? "" : element.getObjectValue().toString();
    }



}
