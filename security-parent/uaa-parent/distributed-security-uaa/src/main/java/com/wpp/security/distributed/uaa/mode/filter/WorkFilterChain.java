package com.wpp.security.distributed.uaa.mode.filter;

/**
 * 过滤链
 *
 * @author wpp
 */
public interface WorkFilterChain<IN, OUT> {
    void doFilter(IN request, OUT response) throws Exception;
}
