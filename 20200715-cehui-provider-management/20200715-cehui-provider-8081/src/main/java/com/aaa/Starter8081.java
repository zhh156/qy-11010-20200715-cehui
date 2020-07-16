package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/14 9:24
 * @Description
 **/
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.aaa.mapper")
public class Starter8081 {
    public static void main(String[] args) {
        SpringApplication.run(Starter8081.class,args);
    }
}
