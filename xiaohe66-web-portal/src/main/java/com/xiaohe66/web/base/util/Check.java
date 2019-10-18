package com.xiaohe66.web.base.util;

import com.xiaohe66.common.ex.XhException;
import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhWebException;
import com.xiaohe66.web.base.exception.param.EmptyException;

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
     * 非null检查,如果传入的对象有一个为null，则抛出{@link XhException}
     *
     * todo : rename to notNull
     * @param objects Object[]
     * @throws XhException 如果传入的对象有一个为null，则抛出该异常
     */
    public static void notNullCheck(Object... objects){
        if(objects == null){
            throw new EmptyException();
        }
        for (Object object : objects) {
            if (object == null){
                throw new EmptyException();
            }
        }
    }

    /**
     * 非空检查，有一个为空的，就抛出异常
     *
     * todo : rename to notEmpty
     * @param objects Object[]
     */
    public static void notEmptyCheck(Object... objects){
        if(objects == null){
            throw new XhWebException(CodeEnum.NULL_EXCEPTION);
        }
        for (Object object : objects) {
            //当对象类型是string时，长度为0时，也表示该对象为空
            boolean isEmpty = object == null||(object instanceof String && ((String) object).length() == 0)
                    ||(object instanceof Collection && ((Collection) object).isEmpty());
            if (isEmpty){
                throw new EmptyException();
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
