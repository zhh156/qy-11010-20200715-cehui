package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/15 14:42
 * @Description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Starter7081 {
    public static void main(String[] args) {
        SpringApplication.run(Starter7081.class,args);
    }
}
