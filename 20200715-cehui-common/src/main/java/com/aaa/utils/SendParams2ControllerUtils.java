package com.aaa.utils;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/26 14:55
 * @Description
 *      从路由发送数据到目标controller，跳转到controller的工具类
 *      并不是只适用于路由，只要是过滤器都可以使用
 **/
public class SendParams2ControllerUtils {
    private SendParams2ControllerUtils(){}

    /**
     * @author Zhao.Hhuan
     * @date 2020/7/26 15:03
     * @description:
     *      发送数据的方法
     * @param bodyJson
	 * @param rcx
	 * @param request
     * @return void
     **/
    public static void sendParams(JSONObject bodyJson, RequestContext rcx, HttpServletRequest request){
        //先将json转换为二进制流
        String body = bodyJson.toString();
        final byte[] bodyBytes = body.getBytes();
        //把request对象放进上下文对象中
        rcx.setRequest(new HttpServletRequestWrapper(request){
            /**
             * @author Zhao.Hhuan
             * @date 2020/7/30 17:30
             * @description:
             *      获取输入流对象
             *      并且把二进制参数传递给该输入流
             *      让其跟着这个输入流硬气传递到目标路径（/doLogin）
             * @param
             * @return javax.servlet.ServletInputStream
             **/
            @Override
            public ServletInputStream getInputStream(){
                return new ServletInputStreamWrapper(bodyBytes);
            }

            /**
             * @author Zhao.Hhuan
             * @date 2020/7/30 17:32
             * @description:
             *      下面的两个方法都是获取参数的长度
             *      只是单位不一样（int，long）
             *      重写这两个方法的意义就是实现request（post）的分段传递
             *      假设：
             *          现在参数非常长
             *          request无论是post还是get都是有传输长度上限的
             *          getContentLength()---->假设已经占用长度完毕，参数没有传递完
             *          getContentLengthLong()---->就会继续执行下面的方法继续传输
             *          eg：
             *              123456789
             *              getContentLength()---->最多传输6位（123456--->并没有将参数全部传递到后端）
             *              --->跳出上面的方法，执行下面的方法---->getContentLengthLong（）---->最多能够传输11位
             *              ---->分段传输123，456,789
             *
             * @param
             * @return int
             **/
            @Override
            public int getContentLength(){
                return bodyBytes.length;
            }

            @Override
            public long getContentLengthLong() {
                return bodyBytes.length;
            }
        });
    }

}
