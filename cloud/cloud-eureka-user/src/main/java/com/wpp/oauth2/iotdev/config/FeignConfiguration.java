package com.wpp.oauth2.iotdev.config;

import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangpp
 */
@Configuration
public class FeignConfiguration {
    /**
     * 设置Basic认证头
     *
     * @param username
     * @param password
     * @return
     */
    //    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(
            @Value( "${cloud.service.user.username}" ) String username,
            @Value( "${cloud.service.user.password}" ) String password) {
        return new BasicAuthRequestInterceptor(username, password);
    }

    /**
     * 设置超时时间
     *
     * @return
     */
//    @Bean
    public Request.Options options() {
        return new Request.Options(1, 1);
    }

}
