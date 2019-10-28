package com.xiaohe66.web.base.util;

import com.xiaohe66.web.base.exception.param.MissingParamException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期相关工具类
 *
 * 日期格式化SimpleDateFormat类有线程安全问题，需要同步
 *
 * @author xh
 * @date 18-03-19 019
 */
public class DateUtils {

    public static synchronized String currentDateTime(){
        return getDateTimeFormat().format(new Date());
    }

    public static synchronized String currentDate(){
        return getDateFormat().format(new Date());
    }

    public static synchronized String currentTime(){
        return getTimeFormat().format(new Date());
    }

    public static String formatDateTime(Date date){
        if(date == null){
            throw new MissingParamException("date");
        }
        return getDateTimeFormat().format(date);
    }

    /***
     *
     * @param format    日期格式表达式
     * @return  当前时间的对应表达式的字符串
     */
    public static String format(String format){
        return new SimpleDateFormat(format).format(new Date());
    }

    private static SimpleDateFormat getDateTimeFormat(){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    private static SimpleDateFormat getDateFormat(){
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    private static SimpleDateFormat getTimeFormat(){
        return new SimpleDateFormat("HH:mm:ss");
    }
}
