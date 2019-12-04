package com.wpp.oauth2.iotdev.filter.error;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangpp
 */
@Configuration
public class ErrorFilterConfiguration {
    @Bean
    @ConditionalOnProperty( prefix = "filter.test", value = "error", havingValue = "true" )
    public MakeErrorFilter MakeErrorFilter() {
        return new MakeErrorFilter();
    }

    @Bean
    @ConditionalOnProperty( prefix = "filter.test", value = "error", havingValue = "true" )
    public HandlerErrorFilter HandlerErrorFilter() {
        return new HandlerErrorFilter();
    }
}
