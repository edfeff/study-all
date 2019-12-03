package com.wpp.security.distributed.uaa.mode.filter;

/**
 * 过滤器
 *
 * @author wpp
 */
public interface WorkFilter<IN, OUT> {
    /**
     * 初始化配置
     *
     * @param config
     * @throws Exception
     */
    default void init(WorkFilterConfig config) throws Exception {
    }

    /**
     * 过滤
     *
     * @param in
     * @param out
     * @param chain
     * @throws Exception
     */
    void doFilter(IN in, OUT out, WorkFilterChain chain) throws Exception;

    /**
     * 销毁
     */
    default void destroy() {
    }
}
