package com.strive.springcloud.cotroller;

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

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/dept/add")
    public Boolean addDept(Dept dept){
        return deptClientService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id")Long id){
        return deptClientService.queryById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return deptClientService.queryAll();
    }

    @GetMapping("/dept/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();

        System.out.println("services: " + services);

        List<ServiceInstance> instances = discoveryClient.getInstances("springcloud-provider-dept");

        for (ServiceInstance instance : instances) {
            System.out.println(
                    instance.getHost() + "\t" +
                    instance.getPort() + "\t" +
                    instance.getServiceId() + "\t" +
                    instance.getInstanceId()
            );
        }

        return discoveryClient;
    }
}
