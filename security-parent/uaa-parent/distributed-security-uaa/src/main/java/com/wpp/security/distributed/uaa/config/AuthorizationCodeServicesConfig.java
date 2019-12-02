package com.wpp.security.distributed.uaa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

import javax.sql.DataSource;

/**
 * @author wangpp
 */
@Configuration
public class AuthorizationCodeServicesConfig {
    @Bean
    AuthorizationCodeServices authorizationCodeServices(DataSource dataSource) {
        JdbcAuthorizationCodeServices services = new JdbcAuthorizationCodeServices(dataSource);
        return services;
//        return new InMemoryAuthorizationCodeServices();
    }
}
