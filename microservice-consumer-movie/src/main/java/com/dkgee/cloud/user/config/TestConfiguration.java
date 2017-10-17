package com.dkgee.cloud.user.config;

import com.dkgee.cloud.ribbon.config.RibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Configuration;

/**
 * 描述：使用RibbonClient，为特定name的Ribbon Client自定义配置。使用@RibbonClient的configuration属性，指定Ribbon的配置类。
 * 作者: JinHuaTao
 * 时间：2017/10/17 14:27
 */
/*@Configuration
@RibbonClient(name = "microservice-provider-user", configuration = RibbonConfiguration.class)*/
public class TestConfiguration {
}
