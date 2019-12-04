package com.wpp.oauth2.iotdev.filter.error;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import com.wpp.oauth2.iotdev.filter.FilterType;

/**
 * @author wangpp
 */
public class MakeErrorFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterType.PRE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //抛出异常
        System.out.println(1 / 0);
        return null;
    }
}
