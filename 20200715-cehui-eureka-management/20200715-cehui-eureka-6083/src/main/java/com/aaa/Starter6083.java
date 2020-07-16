package com.aaa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Company AAA软件教育
 * @Author Zhao.Hhuan
 * @Date Create in 2020/7/14 9:01
 * @Description
 **/
@SpringBootApplication
@EnableEurekaServer
public class Starter6083 {
    public static void main(String[] args) {
        SpringApplication.run(Starter6083.class,args);
    }
}
