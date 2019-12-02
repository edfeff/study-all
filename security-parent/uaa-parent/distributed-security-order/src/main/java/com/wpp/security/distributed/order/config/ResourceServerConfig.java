package com.wpp.security.distributed.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

/**
 * @author wangpp
 */
@Configuration
@EnableConfigurationProperties( UAAServerProperties.class )
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Autowired
    UAAServerProperties uaaServerProperties;

    /**
     * 配置客户端
     *
     * @param resources
     * @throws Exception
     */

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //资源id
        resources.resourceId(uaaServerProperties.getResourceId())
//                验证令牌的服务
                .tokenServices(tokenServices(uaaServerProperties))
                .stateless(true);
    }

    /**
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/oauth2").permitAll()
//                资源的scope
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and()
                .csrf().disable()
//                使用token验证，不用记录Session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Autowired
    public ResourceServerTokenServices tokenServices(UAAServerProperties props) {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl(props.getCheckTokenUrl());
        services.setClientId(props.getClientId());
        services.setClientSecret(props.getClientSecret());
        return services;
    }
}
