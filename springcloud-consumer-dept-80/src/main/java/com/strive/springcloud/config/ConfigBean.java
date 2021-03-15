package com.strive.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 3:12 下午
 * @Description TODO
 */
@Configuration
public class ConfigBean {
    //配置负载均衡实现RestTemplate
    //IRule 接口
    //RandomRule    随机
    //AvailabilityFilteringRule 会先过滤掉，跳闸，访问故障的服务～对剩下的进行轮询
    //RoundRobinRule    轮询
    //RetryRule 会先轮询获取服务，如果服务获取失败，则在制定时间内进行，重试
    @Bean
    @LoadBalanced//Ribbon
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

//    @Bean   //使用随机算法
//    public IRule myRule(){
//        return new RandomRule();
//    }
}
