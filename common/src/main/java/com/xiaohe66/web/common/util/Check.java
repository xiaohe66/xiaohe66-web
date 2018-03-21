package com.xiaohe66.web.common.util;

/**
 * @author xiaohe
 * @time 17-10-29 029
 */
public class Check {

    public static boolean isNull(Object obj){
        return obj == null;
    }

    public static boolean isNotNull(Object obj){
        return obj != null;
    }

    public static boolean isNotEmpty(Object[] obj){
        return obj != null && obj.length != 0;
    }

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

    public static boolean isOneNull(Object... objects){
        return !isAllNotNull(objects);
    }

}
