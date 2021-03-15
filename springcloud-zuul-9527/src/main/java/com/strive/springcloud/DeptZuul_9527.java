package com.strive.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author duxiaopeng
 * @Date 2021/3/15 2:39 下午
 * @Description TODO
 */
@SpringBootApplication
@EnableZuulProxy
public class DeptZuul_9527 {
    public static void main(String[] args) {
        SpringApplication.run(DeptZuul_9527.class, args);
    }
}
