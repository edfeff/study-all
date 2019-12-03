package com.wpp.oauth2.iotdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class UserEureka {
    public static void main(String[] args) {
        SpringApplication.run(UserEureka.class, args);
    }
}
