package com.aaa.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
* @author zhh
* @date 2020/7/9 19:58
* @PropertySource这个注解的作用是加载某一个properties文件
*/
@Component
@ConfigurationProperties(prefix = "redis")
@PropertySource("classpath:properties/redis_cluster.properties")
@Data
public class RedisClusterProperties {
    private String nodes;
    private Integer maxAttempts;
    private Integer commandTimeout;
}
