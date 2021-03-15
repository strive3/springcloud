package com.strive.springcloud.service;

import com.strive.springcloud.pojo.Dept;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 2:18 下午
 * @Description TODO
 */
public interface DeptService {

    public boolean addDept(Dept dept);

    Dept queryById(Long id);

    List<Dept> queryAll();
}
