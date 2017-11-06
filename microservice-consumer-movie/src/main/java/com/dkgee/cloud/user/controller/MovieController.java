package com.dkgee.cloud.user.controller;

import com.dkgee.cloud.user.feign.UserFeignClient;
import com.dkgee.cloud.user.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.mvc.HypermediaDisabled;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * 描述：
 * 作者: JinHuaTao
 * 时间：2017/10/16 9:26
 */
@RestController
public class MovieController {


//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @GetMapping("/user/{id}")
//    public User findById(@PathVariable Long id) {
//        return this.restTemplate.getForObject("http://localhost:8000/" + id, User.class);
//    }
//
//    /**
//     * 查询microservice-provider-user服务的信息并返回
//     * */
//    @GetMapping("/user-instance")
//    public List<ServiceInstance> showInfo(){
//        return this.discoveryClient.getInstances("microservice-provider-user");
//    }

/////////////////////////////////////////////////////////////////////////////////////////////////////
    private static final Logger LOG = LoggerFactory.getLogger(MovieController.class);

    /*@Autowired
    private RestTemplate restTemplate;*/

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    /*@HystrixCommand(fallbackMethod = "findByIdFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
    })
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return this.restTemplate.getForObject("http://microservice-provider-user/" + id, User.class);
    }*/

    @Autowired
    private UserFeignClient userFeignClient;

    //使用Feign实现声明式REST调用; 使用Hystrix实现断路器，容错
   /* @HystrixCommand(fallbackMethod = "findByIdFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
    })*/
//    @HystrixCommand(fallbackMethod = "findByIdFallback")
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
            return this.userFeignClient.findByUserId(id);
    }


    @GetMapping("/log-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservice-provider-user");
        //打印当前选择的是哪个节点
        MovieController.LOG.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());

        /**
         * 不能讲 restTemplate.getForObject(...) 与loadBalancerClient.choose(...)写在同一个方法中，两者之间或有冲突，
         * 因为此时代码中的restTemplate实际上是一个Ribbon客户端，本身包含choose行为。
         * */
    }

   /* public User findByIdFallback(Long id){
        User user = new User();
        user.setId(-1L);
        user.setName("默认用户");
        return user;
    }*/






}
