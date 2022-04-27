package com.atguigu.educms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient  //nacos注册
@EnableFeignClients
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class CmsApplication {
    public static void main(String[] args){
        SpringApplication.run(CmsApplication.class,args);
    }
}
