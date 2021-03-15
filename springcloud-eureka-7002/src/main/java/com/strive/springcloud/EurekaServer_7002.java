package com.strive.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 5:09 下午
 * @Description TODO
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServer_7002 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7002.class, args);
    }
}
