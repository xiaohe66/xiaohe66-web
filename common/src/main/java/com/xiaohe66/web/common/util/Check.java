package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;

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

    public static void notEmptyCheck(Object... objects){
        if(objects == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION);
        }
        for (Object object : objects) {
            //当对象类型是string时，长度为0时，也表示该对象为空
            boolean isEmpty = object == null||(object instanceof String && ((String) object).length() == 0);
            if (isEmpty){
                throw new XhException(CodeEnum.NULL_EXCEPTION);
            }
        }
    }

}
