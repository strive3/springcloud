package com.strive.springcloud.cotroller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.strive.springcloud.pojo.Dept;
import com.strive.springcloud.service.DeptClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 2:20 下午
 * @Description TODO
 */
@RestController
public class DeptController {
    @Autowired
    private DeptClientService deptClientService;

    @GetMapping("/dept/get/{id}")
    @HystrixCommand(fallbackMethod = "HystrixDept")
    public Dept get(@PathVariable("id")Long id){
        Dept dept = deptClientService.queryById(id);
        if (dept == null){
            throw new RuntimeException("该" + id + "不存在");
        }
        return dept;
    }
    //备选方法
    public Dept HystrixDept(@PathVariable("id")Long id){
        return new Dept().setDept_name("该" + id + "不存在").setDb_source("not this MySQL");
    }
}
