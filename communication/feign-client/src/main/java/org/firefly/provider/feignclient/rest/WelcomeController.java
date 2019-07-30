package org.firefly.provider.feignclient.rest;

import org.firefly.provider.feignclient.domain.GreetDTO;
import org.firefly.provider.feignclient.service.WelcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Welcome")
public class WelcomeController {
    // 注入的是一个代理对象，代理客户端完成远程服务方法的调用。
    @Autowired
    private WelcomeService welcomeService;

    @GetMapping("/Greeting/{name}")
    public GreetDTO greet(@PathVariable String name) {
        return welcomeService.greet(name);
    }
}
