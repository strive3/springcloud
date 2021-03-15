package com.strive.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author duxiaopeng
 * @Date 2021/3/15 9:20 上午
 * @Description Ribbon和Feign集成后 客户端可以直接调用，不用关心IP地址和端口号
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.strive.springcloud"})
public class DeptConsumer_Feign80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_Feign80.class, args);
    }
}
