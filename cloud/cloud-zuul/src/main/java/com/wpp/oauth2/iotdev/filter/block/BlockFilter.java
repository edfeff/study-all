package com.wpp.oauth2.iotdev.filter.block;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wpp.oauth2.iotdev.filter.FilterType;

/**
 * 拦截请求
 *
 * @author wangpp
 */
public class BlockFilter extends ZuulFilter {
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
        RequestContext currentContext = RequestContext.getCurrentContext();
        //禁止转发      参考RibbonRoutingFilter
        currentContext.setSendZuulResponse(false);
        //禁止本地转发 参考SendForwardFilter
        currentContext.set("sendForwardFilter.ran", true);
//        设置返回内容
        currentContext.getResponse().setContentType("text/plain;charset=utf-8;");
        currentContext.setResponseBody("请求被拦截");
//        避免后面的拦截器继续过滤此请求
        currentContext.set("isSuccess", false);
        return null;
    }
}
