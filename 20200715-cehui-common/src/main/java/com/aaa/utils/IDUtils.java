package com.aaa.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/14 10:03
 * @Description
 *      获取uuid
 **/
public class IDUtils {
    private IDUtils(){

    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/14 10:04
     * @description:
     *      为es添加数据提供uuid
     * @param
     * @return java.lang.String
     **/
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 21:08
     * @description:
     *      通过时间对id进行指定
     * @param
     * @return java.lang.String
     **/
    public static String getTimeId(){
        //1.获取当前系统时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        //2.创建随机数对象
        Random random = new Random();
        //3.随机从0-999之间随机
        Integer i = random.nextInt(999);
        //4.生成最终的文件名
        /**
         * format():
         *      格式化方法
         *      %：占位符
         *      03：三位，如果不够三位则向前补0
         *      0-999随机：11->011,9->009
         *      d：数字
         */
        return currentTimeMillis + String.format("%06d",i);
    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/19 21:11
     * @description:
     *     随机对id进行指定
     * @param
     * @return double
     **/
    public static double getRandmId(){
        double mathName = Math.random() * 100;
        mathName=new Random().nextInt(1000);
        return mathName;
    }
}
