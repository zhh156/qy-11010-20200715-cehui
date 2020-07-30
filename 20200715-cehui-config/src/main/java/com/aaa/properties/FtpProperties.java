package com.aaa.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
* @author zhh
* @date 2020/7/10 16:51
* ftp自定义属性读取类
*/
@Component
@PropertySource("classpath:properties/ftp.properties")
@ConfigurationProperties(prefix = "ftp")
@Data
public class FtpProperties {

    private  String host;
    private  Integer port;
    private  String username;
    private  String password;
    private  String basePath;
    private  String httpPath;
}
