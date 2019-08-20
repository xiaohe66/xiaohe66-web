package com.xiaohe66.web.cache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;

/**
 * @author xh
 * @date 18-10-27 027
 */
public class XhCache {

    private static final String CFG_XML_PATH = "com/xiaohe66/web/cache/ehcache.xml";

    private static final String FIVE_MINUTES_CACHE = "five_minutes_cache";
    private static final String THIRTY_MINUTES_CACHE = "thirty_minutes_cache";

    private static volatile XhCache xhCache;

    private Cache cache5;
    private Cache cache30;

    private XhCache(){
        CacheManager cacheManager = CacheManager.create(XhCache.class.getClassLoader().getResourceAsStream(CFG_XML_PATH));
        cache5 = cacheManager.getCache(FIVE_MINUTES_CACHE);
        cache30 = cacheManager.getCache(THIRTY_MINUTES_CACHE);
    }

    public static XhCache getInstance(){
        if(xhCache == null){
            synchronized (XhCache.class){
                if(xhCache == null){
                    xhCache = new XhCache();
                }
            }
        }
        return xhCache;
    }

    public Cache getCache5(){
        return cache5;
    }

    public Cache getCache30(){
        return cache30;
    }

}
