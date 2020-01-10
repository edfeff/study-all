package com.wpp;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangpp
 */
@Configuration
@EnableFeignClients(basePackages = "com.wpp.feign")
public class FeignConfiguration {
}
