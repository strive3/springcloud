package com.strive.springcloud.dao;

import com.strive.springcloud.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 2:09 下午
 * @Description TODO
 */
@Mapper
@Repository
public interface DeptDao {
    public boolean addDept(Dept dept);

    Dept queryById(Long id);

    List<Dept> queryAll();
}
