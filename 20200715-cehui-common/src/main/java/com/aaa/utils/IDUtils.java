package com.aaa.utils;

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
}
