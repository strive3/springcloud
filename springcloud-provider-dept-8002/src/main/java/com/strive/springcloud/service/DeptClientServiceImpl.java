package com.strive.springcloud.service;

import com.strive.springcloud.dao.DeptDao;
import com.strive.springcloud.pojo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 2:19 下午
 * @Description TODO
 */
@Service
public class DeptClientServiceImpl implements DeptClientService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }

    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }

    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }
}
