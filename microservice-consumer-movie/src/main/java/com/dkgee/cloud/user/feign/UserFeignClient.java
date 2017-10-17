package com.dkgee.cloud.user.feign;

import com.dkgee.cloud.user.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述：用户Feign接口
 * 作者: JinHuaTao
 * 时间：2017/10/17 18:01
 */
@FeignClient(name = "microservice-provider-user")
public interface UserFeignClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findByUserId(@PathVariable("id") Long id);
}
