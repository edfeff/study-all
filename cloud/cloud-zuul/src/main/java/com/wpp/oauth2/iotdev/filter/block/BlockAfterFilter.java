package com.wpp.oauth2.iotdev.filter.block;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wpp.oauth2.iotdev.filter.FilterType;

/**
 * 在拦截请求器之后,测试是否会经过此过滤器
 *
 * @author wangpp
 */
public class BlockAfterFilter extends ZuulFilter {
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
        //根据上一个过滤器的结果,确定是否过滤
        RequestContext currentContext = RequestContext.getCurrentContext();
        return currentContext.get("isSuccess") == null ? true : (boolean) currentContext.get("isSuccess");
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("after blockingfilter");
        return null;
    }
}
