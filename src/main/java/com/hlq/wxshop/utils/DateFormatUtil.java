package com.hlq.wxshop.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换工具类
 * @Author:HLQ
 * @Date:2019/4/2 20:53
 */
public class DateFormatUtil {

    private static SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat yd = new SimpleDateFormat("yyyy-MM");
    private static SimpleDateFormat datetime = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * 获得以 yyyy-MM-dd 为形式的当前时间字符串
     *
     * @return String
     */
    public static String getCurrentTimeByDay() {
        String time = date.format(new Date(System.currentTimeMillis()));
        return time;
    }

    /**
     * 获得以 yyyy-MM-dd HH:mm:ss 为形式的当前时间字符串
     *
     * @return String
     */
    public static String getCurrentTimeBySecond(Date date) {
        String time = datetime.format(date);
        return time;
    }

    public static String getYearMonth(Date date) {
        String time = yd.format(date);
        return time;
    }
}
