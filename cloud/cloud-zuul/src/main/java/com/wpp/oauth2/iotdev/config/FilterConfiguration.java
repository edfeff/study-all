package com.wpp.oauth2.iotdev.config;

import com.wpp.oauth2.iotdev.filter.BlackIPFilter;
import com.wpp.oauth2.iotdev.filter.IpAddrProperties;
import com.wpp.oauth2.iotdev.filter.WhiteIPFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangpp
 */
@Configuration
@EnableConfigurationProperties( IpAddrProperties.class )
public class FilterConfiguration {

    @Autowired
    IpAddrProperties ipAddrProperties;

    @Bean
    public BlackIPFilter blackIPFilter() {
        return new BlackIPFilter(ipAddrProperties.getBlack());
    }

//    @Bean
    public WhiteIPFilter whiteIPFilter() {
        return new WhiteIPFilter(ipAddrProperties.getWhite());
    }

}
