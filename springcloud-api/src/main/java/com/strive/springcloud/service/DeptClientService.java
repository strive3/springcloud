package com.strive.springcloud.service;

import com.strive.springcloud.pojo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @Author duxiaopeng
 * @Date 2021/3/15 9:53 上午
 * @Description TODO
 */
@Component
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT", fallbackFactory = DeptClientServiceFallRollbackFactory.class)
public interface DeptClientService {
    @PostMapping("/dept/add")
    public boolean addDept(Dept dept);

    @GetMapping("/dept/get/{id}")
    Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    List<Dept> queryAll();
}
