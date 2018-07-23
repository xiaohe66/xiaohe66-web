package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * String 工具类
 *
 * @author xiaohe
 * @time 17-10-29 029
 */
public class StrUtils {

    private static final Logger logger = LoggerFactory.getLogger(StrUtils.class);

    /**
     * 判断字符串是否为null或为空，为null或为空时，返回true，否则返回false
     * @param str
     *      待判断的字符串
     * @return
     *      为null或为空时，返回true，否则返回false
     */
    public static boolean isEmpty(String str){
        return str == null || str.length() == 0;
    }

    /**
     * 判断字符串不为null并且不为空，不为null并且不为空时返回true，否则返回false
     * @param str
     *      待判断的字符串
     * @return
     *      不为null并且不为空时返回true，否则返回false
     */
    public static boolean isNotEmpty(String str){
        return str != null && str.length() != 0;
    }

    /**
     * 判断传入的参数全部不为null并且不为空时返回true，否则返回false
     * @param strs
     *      可变数组，待判断的字符串。
     * @return
     *      判断传入的参数全部不为null并且不为空时返回true，否则返回false。
     *      若传入null或不传，则返回false。
     */
    public static boolean isAllNotEmpty(String... strs){
        if(strs == null || strs.length == 0){
            return false;
        }
        for (String str : strs) {
            if (str == null || str.length() == 0){
                return false;
            }
        }
        return true;
    }

    /**
     * 判断给定的参数中，是否有一个参数是null或者为空。符合时返回true，反之返回false。
     * @param strs
     *      可变数组，待判断的字符串。
     * @return
     *      判断给定的参数中，是否有一个参数是null或者为空。符合时返回true，反之返回false。
     *      若传入null，则返回true。
     */
    public static boolean isOneEmpty(String... strs){
        return !isAllNotEmpty(strs);
    }

    /**
     * 判断2个字符串是否相同，并且不为null。
     * @param str1  字符串1
     * @param str2  字符串2
     * @return
     *      2个字符串不为null，并且相同时，返回true，否则返回false。
     */
    public static boolean equalsAndNotEmpty(String str1, String str2) {
        return str1 != null && str1.length() != 0 && str1.equals(str2);
    }

    /**
     * 返回string的int形式，若转换失败，则返回null
     *
     * @param val
     *      待转换的值
     * @return
     *      转换后的值，若转换失败，则返回null
     */
    public static Integer toIntNotException(String val){
        Integer result = null;
        try {
            result = Integer.parseInt(val);
        }catch (NumberFormatException e){
            logger.error("转换为int失败，待转换的值="+val);
        }
        return result;
    }

    /**
     * 返回string的int形式。若转换失败，会抛出异常。
     *
     * @param val
     *      待转换的值
     * @return
     *      传入参数的int形式
     * @throws XhException  转换失败时，异常此异常
     */
    public static int toInt(String val){
        try {
            return Integer.parseInt(val);
        }catch (NumberFormatException e){
            throw new XhException(CodeEnum.NUMBER_FORMAT_EXCEPTION,"cast failing,val="+val);
        }
    }

    /**
     * 返回string的long形式，若转换失败，则返回null
     * @param val
     *      待转换的值
     * @return
     *      转换后的值，若转换失败，则返回null
     */
    public static Long toLongNotException(String val){
        Long result = null;
        try {
            result = Long.parseLong(val);
        }catch (NumberFormatException e){
            logger.error("转换为long失败，待转换的值="+val);
        }
        return result;
    }

    /**
     * 返回string的long形式，若转换失败，会抛出异常。
     * @param val
     *      待转换的值
     * @return
     *      传入参数的long形式
     * @throws XhException  转换失败时，抛出此异常
     */
    public static long toLong(String val){
        try {
            return Long.parseLong(val);
        }catch (NumberFormatException e){
            throw new XhException(CodeEnum.NUMBER_FORMAT_EXCEPTION,"cast failing,val="+val);
        }
    }

    /**
     * 返回String的Long形式
     * @param values
     * @return
     */
    public static Long[] toLongNotException(String[] values){
        if(values == null){
            return null;
        }
        Long[] result = new Long[values.length];
        for(int i=0;i<values.length;i++){
            result[i] = toLongNotException(values[i]);
        }
        return result;
    }

    public static String nullToStr(String str){
        return str == null ? "" : str;
    }

    /**
     * 去除字符串前面和后面的空格，当传入的字符串为null时，返回空
     * <p>
     * @param str 待清除空格的字符串
     * @return  返回清除前后空格后的字符串，当传入的字符串为null时，返回空。
     */
    public static String trim(String str){
        return str == null ? "" : str.trim();
    }
}
