package com.xiaohe66.web.common.data;

import java.util.Collection;

/**
 * @author xiaohe
 * @time 17-10-29 029
 */
public class CheckUtils {

    public static boolean isAllNotNull(Object... objects){
        if(objects == null){
            return false;
        }
        for (Object str : objects) {
            if (str == null){
                return false;
            }
        }
        return true;
    }

    public static boolean isNull(Object... objects){
        return !isAllNotNull(objects);
    }

    /**
     * 判断集合中的对象是否为null
     * @param collection
     *      集合
     * @return
     *      若集合为null，返回true。若集合中有任一对象为null，返回true。否则返回false。
     */
    public static boolean isOneNull(Collection<?> collection){
        if(collection == null){
            return true;
        }
        for (Object obj : collection) {
            if(obj == null){
                return true;
            }
        }
        return false;
    }
}
