package com.aaa.config;

import feign.Logger;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/21 18:56
 * @Description
 *      springcloud中使用feign实现文件上传功能的配置类
 **/
@Configuration
public class MultipartSupportConfig {
    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConvertersObjectFactory;

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder feignEncoder(){
        return new SpringFormEncoder(new SpringEncoder(messageConvertersObjectFactory));
    }

    @Bean
    public feign.Logger.Level multipartLoggerLevel(){
        return Logger.Level.FULL;
    }

}
