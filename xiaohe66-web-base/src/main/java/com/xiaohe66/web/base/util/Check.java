package com.xiaohe66.web.base.util;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhException;

import java.util.Collection;

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

    /**
     * 非空检查，有一个为空的，就抛出异常
     * @param objects
     */
    public static void notEmptyCheck(Object... objects){
        if(objects == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION);
        }
        for (Object object : objects) {
            //当对象类型是string时，长度为0时，也表示该对象为空
            boolean isEmpty = object == null||(object instanceof String && ((String) object).length() == 0)
                    ||(object instanceof Collection && ((Collection) object).size() == 0);
            if (isEmpty){
                throw new XhException(CodeEnum.NULL_EXCEPTION);
            }
        }
    }

    public static boolean eq(Integer a,Integer b){
        return a == null ? b == null : a.equals(b);
    }

    public static boolean eq(Long a,Long b){
        return a == null ? b == null : a.equals(b);
    }

    public static boolean eq(Float a,Float b){
        return a == null ? b == null : a.equals(b);
    }

    public static boolean eq(Double a,Double b){
        return a == null ? b == null : a.equals(b);
    }

}
