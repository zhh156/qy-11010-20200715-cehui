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
 * @Description
 *      post请求方式获取请求地址中的参数
 **/
public class PostParamsUtils {
    private PostParamsUtils(){

    }

    public static JSONObject postParams(RequestContext requestContext) throws IOException {
        String body =  null;
        if(!requestContext.isChunkedRequestBody()){
            ServletInputStream inputStream;
            inputStream = requestContext.getRequest().getInputStream();
            if(null != inputStream){
                body = IOUtils.toString(inputStream);
            }
        }
        return JSON.parseObject(body);
    }

}
