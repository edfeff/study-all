package com.wpp.oauth2.iotdev.filter.passvalue;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangpp
 */
@Configuration
public class A2BPassValueConfiguration {
    @Bean
    @ConditionalOnProperty( prefix = "filter.test", name = "passvalue", havingValue = "true" )
    public AFilter aFilter() {
        return new AFilter();
    }

    @Bean
    @ConditionalOnProperty( prefix = "filter.test", name = "passvalue", havingValue = "true" )
    public BFilter bFilter() {
        return new BFilter();
    }
}
