package com.zhx.order.config;

import feign.Contract;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局配置：使用@Configuration 会将配置作用于所有的服务提供方
 * 局部配置：如果之针对某一个服务进行配置，不要加@Configuration,并且在@FeignClient中设置 Configuration属性
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
