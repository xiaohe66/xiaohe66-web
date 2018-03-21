package com.xiaohe66.web.common.util;

import com.xiaohe66.web.common.data.CodeEnum;
import com.xiaohe66.web.common.exception.XhException;

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
}
