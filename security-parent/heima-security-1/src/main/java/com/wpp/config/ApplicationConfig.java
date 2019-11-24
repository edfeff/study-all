package com.wpp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * @author wangpp
 */
@Configuration
@ComponentScan( basePackages = "com.wpp", excludeFilters = {
        @ComponentScan.Filter( type = FilterType.ANNOTATION, value = Controller.class )
} )
public class ApplicationConfig {
//连接池 事务管理器

}
