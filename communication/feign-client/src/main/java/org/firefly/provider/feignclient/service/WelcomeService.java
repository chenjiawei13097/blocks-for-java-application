package org.firefly.provider.feignclient.service;

import org.firefly.provider.feignclient.configuration.FeignClientConfiguration;
import org.firefly.provider.feignclient.configuration.WelcomeServiceFallbackFactory;
import org.firefly.provider.feignclient.domain.GreetDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(
        // Feign Client必须定义name或value，否则应用启动时会报错。
        name = "welcome-service",
        // 指定提供HTTP服务的服务器和端口号。
        // 如果不指定，那么name就是服务器对应的主机名，采用http://协议，并使用默认端口。
        url = "http://${feign.client.host}:${feign.client.port}",
        // 不同API调用的公共路径。
        path = "/Welcome",
        // 指定Feign Client配置。
        configuration = FeignClientConfiguration.class,
        // 此属性用于指定处理熔断降级的类，即当依赖的微服务不可用时，需要使用它定义一个替代服务。
        // fallback属性也用于此图，区别在于使用fallbackFactory还可以了解导致回退触发的原因，实际使用中二者居其一即可。
        // 参考：https://www.wandouip.com/t5i90502/
        fallbackFactory = WelcomeServiceFallbackFactory.class)
public interface WelcomeService {
    @RequestMapping(path = "/Greeting/{name}", method = RequestMethod.GET)
    GreetDTO greet(@PathVariable String name);
}
