package com.strive.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 9:10 下午
 * @Description TODO
 */
@Configuration
public class StriveRule {

    @Bean
    public IRule myRule(){
        return new StriveRuleNew();//默认是轮询，现在定义为自定义的负载均衡策略
    }
}
