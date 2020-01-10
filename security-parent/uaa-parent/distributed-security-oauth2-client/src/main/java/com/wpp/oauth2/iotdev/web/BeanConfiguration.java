package com.wpp.oauth2.iotdev.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;


/**
 * @author wpp
 */
@Configuration
public class BeanConfiguration {
    @Value("${jwtkey:wpp123456}")
    String jwtKey;

    @Bean
    public JwtAccessTokenConverterExt jwtAccessTokenConverter() {
        JwtAccessTokenConverterExt jwtAccessTokenConverter = new JwtAccessTokenConverterExt();
        jwtAccessTokenConverter.setSigningKey(jwtKey);
        return jwtAccessTokenConverter;
    }

    static class JwtAccessTokenConverterExt extends JwtAccessTokenConverter {
        public OAuth2Authentication readAuthentication(String token) {
            return extractAuthentication(decode(token));
        }
    }
}