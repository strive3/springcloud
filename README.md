# springcloud

### Eureka

- Eureka本身属于CS架构，客户端服务端
- 自我保护机制：好死不如赖活着，某个时刻服务不可用，Eureka不会对起立即清理，依旧会对该服务的信息进行保存，宁可保留所有的微服务，也不盲目的注销可能健康的服务实例
  。做到了CAP理论中的AP，强调的是服务的高可用。
  
```yaml
#eureka 服务端主要的配置
eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    register-with-eureka: true #表示是否向eureka注册中心注册自己
    fetch-registry: false #fetch-registry为false表示自己是注册中心
    service-url: #监控页面
      #单机：http://${eureka.instance.hostname}:${server.port}/eureka/
      #集群：关联 每台地址 用 , 分割
      defaultZone: http://localhost:7001/eureka/
```
服务端开启的注解 ```@EnableEurekaServer//服务端启动类```

### Ribbon/Feign

- Feign集成了Ribbon，面向的是接口和注解
- Ribbon+RestTemplate面向的是服务
```java
//关键注解是  注解在方法上
//配置负载均衡实现RestTemplate
//IRule 接口
//RandomRule    随机
//AvailabilityFilteringRule 会先过滤掉，跳闸，访问故障的服务～对剩下的进行轮询
//RoundRobinRule    轮询  -->默认
//RetryRule 会先轮询获取服务，如果服务获取失败，则在制定时间内进行，重试
@Bean
@LoadBalanced//Ribbon
public RestTemplate getRestTemplate(){
    return new RestTemplate();
}
```
//启动类上需要的开启注解
```java
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name = "springcloud-provider-dept", configuration = StriveRule.class)
public class DeptConsumer_80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer_80.class, args);
    }
}
```
如果需要自定义负载均衡策略，需要实现  ```AbstractLoadBalancerRule```这个类

### Hystrix
