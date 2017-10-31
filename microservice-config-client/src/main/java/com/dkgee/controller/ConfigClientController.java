package com.dkgee.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 * 作者：JinHuaTao
 * 时间：2017/10/30 14:15
 */
@RefreshScope       //配置后可动态刷新配置文件(手动刷新)
@RestController
public class ConfigClientController {

    @Value("${profile}")
    private String profile;

    @Value("${ctfx.single.task.enable}")
    private boolean enable;

    @GetMapping("/profile")
    public String hello(){
        return this.profile + "##" + this.enable;
    }

}
