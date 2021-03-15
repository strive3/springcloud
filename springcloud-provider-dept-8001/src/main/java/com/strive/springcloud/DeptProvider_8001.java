package com.strive.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 2:41 下午
 * @Description TODO
 */
@SpringBootApplication
@EnableEurekaClient //在服务启动后自动注册到eureka 中
public class DeptProvider_8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider_8001.class ,args);
    }
}
