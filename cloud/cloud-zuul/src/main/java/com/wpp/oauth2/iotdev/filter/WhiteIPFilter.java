package com.wpp.oauth2.iotdev.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;

import java.util.Set;

/**
 * @author wangpp
 */
public class WhiteIPFilter extends ZuulFilter {
    private Set<String> whiteIpSet;

    public WhiteIPFilter(Set<String> whiteIpSet) {
        this.whiteIpSet = whiteIpSet;
    }

    @Override
    public String filterType() {
        return "pre";
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
        String ip = currentContext.getRequest().getRemoteAddr();
        //不在白名单中
        if (StringUtils.isNotBlank(ip) && !whiteIpSet.contains(ip)) {
            currentContext.setSendZuulResponse(false);
            currentContext.setResponseBody("非法请求");
            currentContext.getResponse().setContentType("text/plain;charset=utf-8");
            return null;
        }
        return null;
    }
}
