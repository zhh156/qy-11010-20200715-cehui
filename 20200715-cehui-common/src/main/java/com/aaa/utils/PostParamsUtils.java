package com.aaa.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.context.RequestContext;
import io.micrometer.core.instrument.util.IOUtils;

import javax.servlet.ServletInputStream;
import java.io.IOException;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/26 14:37
 * @Description post请求方式获取请求地址中的参数
 **/
public class PostParamsUtils {
    private PostParamsUtils() {

    }

    /**
     * @param requestContext
     * @return com.alibaba.fastjson.JSONObject
     * @author Zhao.Hhuan
     * @date 2020/7/30 16:48
     * @description: 获取POST请求形式的参数方法
     **/
    public static JSONObject postParams(RequestContext requestContext) {
        //其实就是通过body属性把post请求形式参数加载进来
        String body = null;
        /**
         * isChunkedRequestBody()：
         *      你就必须要知道与@RequestBody注解有关
         *      因为你前端向后端传递json/对象数据的时候，后端通常在形参中会使用一个注解@RequestBody
         *      应为前端只能传递json数据，并不能直接传递对象数据
         *      ---->当前端把这个json对象传递给后端的时候，后端需要把json转换为自己认识的实体类对象
         *      那么最终JVM会调用反序列化器---->把二进制流转换为实体对象
         *      其实spring框架就是依靠@RequestBody来进行把二进制流对象反序列化成实体对象
         *      isChunkedRequestBody()最终意义就是该接收的数据是否是一个被@RequestBody 注解所标识的数据
         */
        if (!requestContext.isChunkedRequestBody()) {
            //说明并不是@RequestBody注解所标识的数据
            ServletInputStream inputStream;
            try {
                //既然没有自动装换，所以我会手动使用IO流来进行转换
                inputStream = requestContext.getRequest().getInputStream();
                if (null != inputStream) {
                    //因为ServletInputStream输入流对象程序员无法操作
                    //调用IO工具类把ServletInputStream对象转换为String类型的字符串
                    body = IOUtils.toString(inputStream);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return JSON.parseObject(body);
    }

}
