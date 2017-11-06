package com.dkgee.cloud.user.feign;

import com.dkgee.cloud.user.entity.User;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述：用户Feign接口
 * 作者: JinHuaTao
 * 时间：2017/10/17 18:01
 */
@FeignClient(name = "microservice-provider-user", fallbackFactory = FeignClientFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    User findByUserId(@PathVariable("id") Long id);
}

/*@Component
class FeignClientFallback implements UserFeignClient {

    @Override
    public User findByUserId(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(-1L);
        user.setName("默认用户");
        return user;
    }
}*/


@Component
class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient>{

    private static final Logger LOG = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

    @Override
    public UserFeignClient create(Throwable throwable) {
        return new UserFeignClient() {
            @Override
            public User findByUserId(Long id) {
                //日志最好放在各个fallback方法中，而不要直接放在create方法中，否则在引用启动时，就会打印日志。
                FeignClientFallbackFactory.LOG.info("fall back,; reason was:", throwable);
                User user = new User();
                user.setId(-1L);
                user.setUsername("默认用户");
                return user;
            }
        };
    }
}
