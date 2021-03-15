package com.strive.springcloud.service;

import com.strive.springcloud.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author duxiaopeng
 * @Date 2021/3/15 11:32 上午
 * @Description TODO
 */
@Component
public class DeptClientServiceFallRollbackFactory implements FallbackFactory {
    @Override
    public DeptClientService create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public boolean addDept(Dept dept) {
                return false;
            }

            @Override
            public Dept queryById(Long id) {
                return new Dept().setDept_name("在客户端进行了服务降级");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }
        };
    }
}
