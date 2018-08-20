package com.xiaohe66.web.base.util;

import com.xiaohe66.web.base.data.CodeEnum;
import com.xiaohe66.web.base.exception.XhException;

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

    private static final SimpleDateFormat DATE_TIME_FORMAT;
    private static final SimpleDateFormat DATE_FORMAT;
    private static final SimpleDateFormat TIME_FORMAT;


    static {
        DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        TIME_FORMAT = new SimpleDateFormat("hh:mm:ss");
    }

    public static synchronized String currentDateTime(){
        return DATE_TIME_FORMAT.format(new Date());
    }

    public static synchronized String currentDate(){
        return DATE_FORMAT.format(new Date());
    }

    public static synchronized String currentTime(){
        return TIME_FORMAT.format(new Date());
    }

    public static String formatDateTime(Date date){
        if(date == null){
            throw new XhException(CodeEnum.NULL_EXCEPTION);
        }
        synchronized (DATE_TIME_FORMAT){
            return DATE_TIME_FORMAT.format(date);
        }
    }

    /***
     *
     * @param format    日期格式表达式
     * @return  当前时间的对应表达式的字符串
     */
    public static String format(String format){
        return new SimpleDateFormat(format).format(new Date());
    }
}
