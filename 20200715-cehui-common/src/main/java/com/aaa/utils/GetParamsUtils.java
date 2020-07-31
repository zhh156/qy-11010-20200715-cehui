package com.aaa.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/26 14:08
 * @Description
 *      获取GET请求路径中的请求参数
 **/
public class GetParamsUtils {
    private GetParamsUtils(){

    }

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/26 14:09
     * @description:
     *      获取请求路径中的参数
     * @param request
     * @return java.lang.String
     **/
    public static String getParams(HttpServletRequest request){
        StringBuilder params = new StringBuilder("?");
        //1.获取参数
        Enumeration<String> parameterNames = request.getParameterNames();
        //2.判断用户使用的是否是get方式
        if(request.getMethod().toUpperCase().equals("GET")){
            //使用的是get方式
            while(parameterNames.hasMoreElements()){
                //3.将参数封装进String中
                String name = parameterNames.nextElement();
                params.append(name);
                params.append("=");
                params.append(request.getParameter(name));
                params.append("&");
            }
        }
        //4.判断是否有参数
        if(params.length() > 1){
            //删除最后一个&或者？
            params.delete(params.length()-1,params.length());
        }
        return params.toString();
    }
}
