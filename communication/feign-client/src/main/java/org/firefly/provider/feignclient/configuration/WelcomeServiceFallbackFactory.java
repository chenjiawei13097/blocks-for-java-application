package org.firefly.provider.feignclient.configuration;

// FallbackFactory类是OpenFeign熔断降级处理机制的重要组成部分。
import feign.hystrix.FallbackFactory;
import org.firefly.provider.feignclient.domain.GreetDTO;
import org.firefly.provider.feignclient.service.WelcomeService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class WelcomeServiceFallbackFactory implements FallbackFactory<WelcomeService> {
    @Override
    public WelcomeService create(Throwable throwable) {
        return new WelcomeService() {
            @Override
            public GreetDTO greet(String name) {
                // throwable.getMessage()是服务回退时的异常信息。
                return new GreetDTO(name, "Blocked when greeting " + name + "."
                        + "\r\nError message: " + throwable.getMessage()
                        + "\r\nStack Track: " + Arrays.asList(throwable.getStackTrace()), 20, LocalDateTime.now());
            }
        };
    }
}
