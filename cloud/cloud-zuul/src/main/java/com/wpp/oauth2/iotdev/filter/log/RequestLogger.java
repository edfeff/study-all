package com.wpp.oauth2.iotdev.filter.log;

import com.netflix.zuul.context.RequestContext;

import java.util.Map;

/**
 * @author wangpp
 */
public interface RequestLogger {
    void log(Map<String, Object> infoMap, RequestContext requestContext);
}
