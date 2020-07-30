package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/26 13:51
 * @Description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy //开启路由
public class MainStarterZuul {
    public static void main(String[] args) {
        SpringApplication.run(MainStarterZuul.class,args);
    }
}
