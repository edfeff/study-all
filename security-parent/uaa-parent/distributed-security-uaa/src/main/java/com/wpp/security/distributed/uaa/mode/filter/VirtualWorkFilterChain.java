package com.wpp.security.distributed.uaa.mode.filter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * 过滤器链子
 *
 * @author wpp
 */
public class VirtualWorkFilterChain<IN, OUT> implements WorkFilterChain<IN, OUT> {
    private static final Log logger = LogFactory.getLog(VirtualWorkFilterChain.class);
    private List<WorkFilter<IN, OUT>> filters;
    int currentPosition = 0;
    int size;

    public VirtualWorkFilterChain(List<WorkFilter<IN, OUT>> filters) {
        if (filters == null || filters.isEmpty()) {
            throw new IllegalArgumentException("filters is empty or null !");
        }
        this.filters = filters;
        this.size = filters.size();
    }

    @Override
    public void doFilter(IN request, OUT response) throws Exception {
        if (currentPosition == size) {
            if (logger.isDebugEnabled()) {
                logger.debug("过滤链执行完毕");
            }
        } else {
            WorkFilter<IN, OUT> filter = filters.get(currentPosition++);
            if (logger.isDebugEnabled()) {
                logger.debug("正在执行过滤,第[ " + currentPosition + " / " + size + " ]个" + filter.toString());
            }
            filter.doFilter(request, response, this);
        }
    }
}
