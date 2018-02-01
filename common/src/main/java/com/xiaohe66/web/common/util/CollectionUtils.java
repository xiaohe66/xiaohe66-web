package com.xiaohe66.web.common.util;

import java.util.Collection;

/**
 * @author xiaohe
 * @time 17-07-29 029
 */
public class CollectionUtils {

    /**
     * 判断一个集合是否不为null
     * @param collection
     *      待判断的集合
     * @return
     *      若传入的参数不为null，则返回true。否则返回false
     */
    public static boolean isNotNull(Collection collection){
        return collection != null;
    }

    /**
     * 判断一个集合是否为null
     * @param collection
     *      待判断的集合
     * @return
     *      若传入的参数为null，则返回true。否则返回false
     */
    public static boolean isNull(Collection collection){
        return collection == null;
    }

    /**
     * 判断一个集合是否不为null，并且集合大小不为0
     * @param collection
     *      待判断的集合
     * @return
     *      若传入的参数不为null，并且内容不为0，则返回true。否则返回false
     */
    public static boolean isNotEmpty(Collection collection){
        return collection != null && collection.size() != 0;
    }

    /**
     * 判断一个集合是否为null，或集合大小为0
     * @param collection
     *      待判断的集合
     * @return
     *      若传入的参数为null，或者集合大小为0，则回返true。否则返回false
     */
    public static boolean isEmpty(Collection collection){
        return collection == null || collection.size() == 0;
    }

}
