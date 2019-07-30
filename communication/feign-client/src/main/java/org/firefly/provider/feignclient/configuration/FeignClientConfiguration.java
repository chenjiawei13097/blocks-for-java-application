package org.firefly.provider.feignclient.configuration;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 参考资料：https://youletter.cn/2018/10/30/feign-client/
 */
@Configuration
public class FeignClientConfiguration {
    private static int CONNECT_TIMEOUT_MILLIS = 10000;
    private static int READ_TIMEOUT_MILLIS = 60000;

    @Bean
    public Request.Options feignOptions() {
        // 配置连接超时和读超时，默认分别为10s和60s
        return new Request.Options(CONNECT_TIMEOUT_MILLIS, READ_TIMEOUT_MILLIS);
    }

    @Bean
    public Retryer feignRetryer() {
        // 最大尝试5次，每次的间隔为：100毫秒*1.5的已尝试次数次方，但最多1s。
        // Spring Cloud Starter OpenFeign默认是不Retry的，需要显式配置指定。
        return new Retryer.Default();
    }
}