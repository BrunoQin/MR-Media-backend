package com.mr.media.util;

import org.springframework.format.datetime.DateFormatter;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间辅助类
 */
public class DateHelper {

    /**
     * 将Date对象格式化为字符串
     */
    public static String format(Date date, String format){
        try {
            return new DateFormatter(format).print(date, Locale.CHINA);
        }catch (Exception e){
            return "";
        }
    }


    /**
     * 将字符串解析为Date对象
     */
    public static Date parse(String text, String format){
        try {
            return new DateFormatter(format).parse(text, Locale.CHINA);
        }catch (Exception e){
            return null;
        }
    }

    /**
     * 将指定Date对象格式化为yyyyMMddHHmmss
     */
    public static String getTimestamp(Date date){
        return format(date, "yyyyMMddHHmmss");
    }



    /**
     * 将指定Date对象的时分秒去除，只保留年月日
     * @param date
     * @return
     */
    public static Date truncateTime(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 把时间转换成年月的形式
     * @param year
     * @param month
     * @return
     */
    public static Date generateDateFromYearAndMonth(int year, int month){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTime();
    }


    /**
     * 将指定Date对象加上指定的天数
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.DAY_OF_YEAR, days);

        return cal.getTime();
    }

    /**
     * 将指定Date对象加上指定的小时数
     */
    public static Date addHour(Date date, int hours){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.HOUR_OF_DAY, hours);

        return cal.getTime();
    }

    /**
     * 将指定Date对象加上指定的分钟数
     */
    public static Date addMinute(Date date, int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MINUTE, minutes);

        return cal.getTime();
    }

    /**
     * 将指定Date对象加上指定的秒数
     */
    public static Date addSecond(Date date, int seconds){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.SECOND, seconds);

        return cal.getTime();
    }

}
