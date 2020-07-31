package com.aaa.utils;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/30 17:44
 * @Description
 *
 **/
public class GetZuulFilterParamsUtils {

    private GetZuulFilterParamsUtils(){

    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/30 17:51
     * @description:
     *
     * @param request
     * @return java.util.Map
     **/
    public static Map getParams(HttpServletRequest request){
        try {
            //1.获取到zuul手动跳转路径中携带的二进制数据流
            ServletInputStream inputStream = request.getInputStream();
            //2.将二进制流对象放进string中
            String jsonString = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
            //3.将string数据转换为map对象
            Map map = JSONUtils.toObject(jsonString,Map.class);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
