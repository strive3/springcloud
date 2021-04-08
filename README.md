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
假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其它的微服务，这就是所谓的“扇出”。如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”。
#### 服务雪崩与服务降级比较 
相同点：
-	目的很一致，都是从可用性可靠性着想，为防止系统的整体缓慢甚至崩溃，采用的技术手段；
-   最终表现类似，对于两者来说，最终让用户体验到的是某些功能暂时不可达或不可用；
-   粒度一般都是服务级别，当然，业界也有不少更细粒度的做法，比如做到数据持久层（允许查询，不允许增删改）；
-   自治性要求很高，熔断模式一般都是服务基于策略的自动触发，降级虽说可人工干预，但在微服务架构下，完全靠人显然不可能，开关预置、配置中心都是必要手段；

不同点：

-	触发原因不太一样，服务熔断一般是某个服务（下游服务）故障引起，而服务降级一般是从整体负荷考虑；
-   管理目标的层次不太一样，熔断其实是一个框架级的处理，每个微服务都需要（无层级之分），而降级一般需要对业务有层级之分（比如降级一般是从最外围服务开始）
-   实现方式不太一样
-   服务降级：客户端，当某个服务熔断活着关闭之后，服务将不再被调用
-   服务熔断：服务端，某个服务超时或者异常，引起熔断

```java
@EnableCircuitBreaker//添加对熔断器的支持
```

### Zuul

提供=代理+路由+过滤三大功能：

Zuul包含了对请求的路由和过滤两个最主要的功能：
其中路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础而过滤器功能则负责对请求的处理过程进行干预，是实现请求校验、服务聚合等功能的基础.

Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他微服务的消息，也即以后的访问微服务都是通过Zuul跳转后获得。
```yaml
zuul:
  routes:
    mydept.serviceId: springcloud-provider-dept
    mydept.path:  /mydept/**
  ignored-services: "*"  #不能使用该路径访问了
  prefix: ""  #设置公共前缀
```

```java
//开启的注解为：
@EnableZuulProxy
```