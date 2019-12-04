package com.wpp.oauth2.iotdev.filter.block;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangpp
 */
@Configuration
public class BlockFilterConfiguration {
    @Bean
    @ConditionalOnProperty( prefix = "filter.test", name = "blocking", havingValue = "true" )
    public BlockFilter blockFilter() {
        return new BlockFilter();
    }

    @Bean
    @ConditionalOnProperty( prefix = "filter.test", name = "blocking", havingValue = "true" )
    public BlockAfterFilter blockAfterFilter() {
        return new BlockAfterFilter();
    }

}
