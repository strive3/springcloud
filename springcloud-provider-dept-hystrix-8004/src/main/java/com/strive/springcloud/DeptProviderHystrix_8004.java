package com.strive.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 2:41 下午
 * @Description TODO
 */
@SpringBootApplication
@EnableEurekaClient //在服务启动后自动注册到eureka 中
@EnableCircuitBreaker//添加对熔断器的支持
public class DeptProviderHystrix_8004 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix_8004.class ,args);
    }

    //增加一个servlet ，用于被监控
    @Bean
    public ServletRegistrationBean getHystrixMetricsStreamServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}
