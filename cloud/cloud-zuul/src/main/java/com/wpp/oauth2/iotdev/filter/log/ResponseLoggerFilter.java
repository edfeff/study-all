package com.wpp.oauth2.iotdev.filter.log;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wangpp
 */
@Component
public class ResponseLoggerFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "post";
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
        HttpServletResponse response = requestContext.getResponse();
        int status = response.getStatus();
        System.out.println("===========================");
        System.out.println("hostURL: " + requestContext.getRouteHost());
        System.out.println("响应码: " + status);
        System.out.println("===========================");
        requestContext.get("zuulResponse");
        return null;

    }
}
