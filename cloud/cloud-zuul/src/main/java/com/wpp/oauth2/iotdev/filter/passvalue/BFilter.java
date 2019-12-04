package com.wpp.oauth2.iotdev.filter.passvalue;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wpp.oauth2.iotdev.filter.FilterType;

/**
 * 过滤器传值 B
 *
 * @author wangpp
 */
public class BFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterType.PRE;
    }

    @Override
    public int filterOrder() {
        return 3;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        System.out.println("B GET " + currentContext.get("A"));
        System.out.println("B GET " + (String) currentContext.get("msg"));
        return null;
    }
}
