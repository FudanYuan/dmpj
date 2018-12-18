package com.dmpj.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 * @Author: Jeremy
 * @Date: 2018/9/6 10:05
 */
public class TimeUtil {
    public static Date yearMonthDay(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * 获取
     * @return
     */
    public static String getTimeSlice(Long sTime, Long eTime){
        SimpleDateFormat time=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return time.format(sTime*1000) + "-" + time.format(eTime*1000);
    }
}
