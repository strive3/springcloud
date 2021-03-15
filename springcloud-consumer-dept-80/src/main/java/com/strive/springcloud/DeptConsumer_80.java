package com.strive.springcloud;

import com.strive.myrule.StriveRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 3:24 下午
 * @Description TODO
 */
//Ribbon 和Eureka 整合之后，客户端可以直接调用，不用关心ip地址和端口号
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "springcloud-provider-dept", configuration = StriveRule.class)
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class, args);
    }
}
