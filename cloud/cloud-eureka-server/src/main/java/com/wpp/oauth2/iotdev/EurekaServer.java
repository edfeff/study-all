package com.wpp.oauth2.iotdev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.eureka.server.EurekaServerMarkerConfiguration;
import org.springframework.context.annotation.Bean;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServer.class, args);
    }

    //    @Bean
    public Object server() {
        EurekaServerMarkerConfiguration configuratio = new EurekaServerMarkerConfiguration();
        return configuratio.eurekaServerMarkerBean();
    }
}
