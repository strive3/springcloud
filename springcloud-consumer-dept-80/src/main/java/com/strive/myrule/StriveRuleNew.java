package com.strive.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author duxiaopeng
 * @Date 2021/3/14 9:28 下午
 * @Description TODO
 */
public class StriveRuleNew extends AbstractLoadBalancerRule {
    public StriveRuleNew() {
    }
    //每个服务访问5次之后调用下一个服务
    private int total;//被调用的次数
    private int currentIndex;//当前是谁在调用服务

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                if (Thread.interrupted()) {
                    return null;
                }

                List<Server> upList = lb.getReachableServers();//获取活着的服务
                List<Server> allList = lb.getAllServers();//获取全部的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

//                int index = this.chooseRandomInt(serverCount);
//                server = (Server)upList.get(index);
                if (total == 5){
                    total = 0;
                    currentIndex ++;
                    if (currentIndex == 2){
                        currentIndex = 0;
                    }
                } else {
                    total ++;
                    server = upList.get(currentIndex);//从活着的服务中获取制定的服务来操作
                }

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
