package org.firefly.provider.feignclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
// @EnableFeignClients用于告诉框架，扫描所有通过注解@FeignClient定义的Feign客户端。
// 具体过程参考：https://blog.csdn.net/andy_zhang2007/article/details/86680622
@EnableFeignClients
public class FeignClientDemo {
    public static void main(String[] args) {
        SpringApplication.run(FeignClientDemo.class, args);
    }
}
