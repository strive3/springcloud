package com.strive.springcloud.controller;

import com.strive.springcloud.pojo.Dept;
import com.strive.springcloud.service.DeptClientService;
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
    @Autowired
    private DeptClientService deptClientService;

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id){
        return deptClientService.queryById(id);
    }

    @RequestMapping("/consumer/dept/add")
    public Boolean add(Dept dept){
        return deptClientService.addDept(dept);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return deptClientService.queryAll();
    }
}
