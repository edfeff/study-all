package com.wpp.security.distributed.uaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangpp
 */

@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
@EnableFeignClients(basePackages = {"com.wpp.security.distributed.uaa"})
@MapperScan(basePackages = "com.wpp.security.distributed.uaa.mapper")
public class UAAServer {
    public static void main(String[] args) {
        SpringApplication.run(UAAServer.class, args);
    }
}
