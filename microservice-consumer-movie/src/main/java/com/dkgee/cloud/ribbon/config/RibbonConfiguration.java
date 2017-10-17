package com.dkgee.cloud.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：该类为Ribbon配置类
 * 注意:该类不应该在主应用程序上下文中的@ComponentScan中
 * 作者: JinHuaTao
 * 时间：2017/10/17 14:23
 */
@Configuration
public class RibbonConfiguration {

    @Bean
    public IRule ribbonRule(){
        //负载均衡规则，改为随机
        return new RandomRule();
    }
}
