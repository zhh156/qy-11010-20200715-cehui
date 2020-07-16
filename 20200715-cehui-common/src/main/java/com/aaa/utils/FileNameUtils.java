package com.aaa.utils;

import java.util.Random;

/**
* @author zhh
* @date 2020/7/10 16:58
* 文件重新命名工具类
*/
public class FileNameUtils {
    private FileNameUtils(){

    }

    /**
     * 文件的生成
     * @return
     */
    public static String getFileName(){
        //1.获取当前系统时间的毫秒数
        long currentTimeMillis = System.currentTimeMillis();
        //2.创建随机数对象
        Random random = new Random();
        //3.随机从0-999之间随机
        int i = random.nextInt(999);
        //4.生成最终的文件名
        /**
         * format():
         *      格式化方法
         *      %：占位符
         *      03：三位，如果不够三位则向前补0
         *      0-999随机：11->011,9->009
         *      d：数字
         */
        return currentTimeMillis + String.format("%03d",i);
    }
}
