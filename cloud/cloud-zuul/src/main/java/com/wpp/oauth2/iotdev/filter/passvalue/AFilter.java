package com.wpp.oauth2.iotdev.filter.passvalue;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wpp.oauth2.iotdev.filter.FilterType;

/**
 * 过滤器传值 A
 *
 * @author wangpp
 */
public class AFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterType.PRE;
    }

    @Override
    public int filterOrder() {
        return 2;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        currentContext.set("A");
        currentContext.set("msg", "A 已经过滤了 ");
        return null;
    }
}
