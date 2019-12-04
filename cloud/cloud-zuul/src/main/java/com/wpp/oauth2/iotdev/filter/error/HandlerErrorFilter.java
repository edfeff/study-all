package com.wpp.oauth2.iotdev.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wpp.oauth2.iotdev.filter.FilterType;

/**
 * @author wangpp
 */
public class HandlerErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterType.ERROR;
    }

    @Override
    public int filterOrder() {
        return 100;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        Throwable throwable = currentContext.getThrowable();
        System.out.println("===============error================");
        System.out.println(throwable.getCause().getMessage());
        System.out.println("===============end of error================");
        return null;
    }
}
