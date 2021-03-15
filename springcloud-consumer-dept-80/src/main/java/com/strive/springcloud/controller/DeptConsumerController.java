package com.strive.springcloud.controller;

import com.strive.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 3:03 下午
 * @Description TODO
 */
@RestController
public class DeptConsumerController {
    //消费者不应该有service层
    //restTemplate。。供我们调用
    @Autowired
    private RestTemplate restTemplate;//提供多种便捷访问远程Http服务的方法，简单的Restful服务模版
    //Ribbon 来实现，这里应该是个变量，通过服务名（spring.application.name）来访问
//    private static final String REST_URL_PREFIX = "http://localhost:8001";
    private static final String REST_URL_PREFIX = "http://springcloud-provider-dept";

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/get/" + id, Dept.class);
    }

    @RequestMapping("/consumer/dept/add")
    public Boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PREFIX + "/dept/add", dept, Boolean.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL_PREFIX + "/dept/list", List.class);
    }
}
