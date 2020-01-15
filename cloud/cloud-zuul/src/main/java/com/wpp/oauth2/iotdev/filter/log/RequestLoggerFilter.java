package com.wpp.oauth2.iotdev.filter.log;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangpp
 */
@Component
public class RequestLoggerFilter extends ZuulFilter {
    @Autowired
    List<RequestLogger> loggers;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1000;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Map<String, Object> infoMap = new HashMap<>();
        for (RequestLogger logger : loggers) {
            logger.log(infoMap, requestContext);
        }

        infoMap.forEach((k, v) -> {
            System.out.println(k + "::" + v);
        });
        return null;
    }
}
