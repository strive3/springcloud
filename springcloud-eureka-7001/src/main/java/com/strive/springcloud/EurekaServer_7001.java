package com.strive.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 4:01 下午
 * @Description TODO
 */
@SpringBootApplication
@EnableEurekaServer//服务端启动类
public class EurekaServer_7001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer_7001.class, args);
    }
}
