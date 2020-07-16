package com.aaa.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* @author zhh
* @date 2020/7/10 18:29
*       日期处理工具类
*/
public class DateUtils {
    private DateUtils(){

    }
    private static final String DATE_TYPE="yyyy-MM-dd";

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 10:14
     * @description:
     *      按照DATE_TYPE的格式来对日期进行转换
     * @param date
     * @return java.lang.String
     **/
    public static final String formatDate(Object date){
        if(date == null){
            return null;
        }else{
            return formatDate(date,DATE_TYPE);
        }
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 10:23
     * @description:
     *      按照自己指定的格式来对日期进行转换
     * @param date
	 * @param formatType
     * @return java.lang.String
     **/
    public static final String formatDate(Object date,String formatType){
        if(date == null){
            return null;
        }else{
            if(StringUtils.isNotEmpty(formatType)){
                //说明最终需要根据客户所定义的格式来进行转换
                SimpleDateFormat format = new SimpleDateFormat(formatType);
                return format.format(date);
            }else {
                //没有给定自己的格式（这里千万不要直接return null-->容易抛出格式转换异常的错误）
                return formatDate(date);
            }
        }
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 10:26
     * @description:
     *      将时间转换为字符串
     * @param millisecond
     * @return java.lang.String
     **/
    public static String formatDateAgo(long millisecond){
        StringBuilder stringBuilder = new StringBuilder();
        if(millisecond < 1000){
            // 仅仅是毫秒
            stringBuilder.append(millisecond).append("毫秒");
        }else{
            //单位为秒
            Integer ss = 1000;
            Integer mi = ss * 60;
            Integer hh = mi * 60;
            Integer dd = hh * 24;

            Long day = millisecond / dd;
            Long hour = (millisecond - day * dd) / hh;
            Long minute = (millisecond - day * dd - hour * hh) / mi;
            Long second = (millisecond - day * dd - hour * hh - minute * mi) / ss;

            if(day > 365){
                return formatDate(new Date(millisecond),"yyyy年MM月dd月 HH时mm分ss秒");
            }
            if(day > 0){
                stringBuilder.append(day).append("天");
            }
            if(hour > 0){
                stringBuilder.append(hour).append("时");
            }
            if(minute > 0){
                stringBuilder.append(minute).append("分");
            }
            if(second > 0){
                stringBuilder.append(second).append("秒");
            }
        }
        return stringBuilder.toString();
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 10:48
     * @description:
     *      获取当前系统的时间
     * @param
     * @return java.lang.String
     **/
    public static final String getCurrentDate(){
        return formatDate(new Date());
    }
    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 10:49
     * @description:
     *      获取当年年度
     * @param
     * @return java.lang.Integer
     **/
    public static Integer getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

}
