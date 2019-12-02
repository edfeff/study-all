package com.wpp.security.distributed.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author wangpp
 */
@Data
@ConfigurationProperties( "uaa.config" )
public class UAAServerProperties {
    private String clientId;
    private String clientSecret;
    private String checkTokenUrl;
    private String resourceId;
}

