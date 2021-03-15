package com.strive.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @Author duxiaopeng
 * @Date 2021/3/15 12:32 下午
 * @Description TODO
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DeptHystrixDashBoard_9001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptHystrixDashBoard_9001.class, args);
    }
}
