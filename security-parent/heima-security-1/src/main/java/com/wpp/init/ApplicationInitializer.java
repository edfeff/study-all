package com.wpp.init;

import com.wpp.config.ApplicationConfig;
import com.wpp.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @author wangpp
 */
public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //spring 容器
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    //servletContext
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //url-mapping
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
