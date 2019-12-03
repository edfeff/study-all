package com.wpp.security.distributed.uaa.mode.filter;

/**
 * @author wpp
 */
public class AbstractWorkFilterAdapter<IN, OUT> extends AbstractWorkFilter<IN, OUT> {
    @Override
    protected void exceptionFilter(IN in, OUT out, Exception e) {

    }

    @Override
    protected void finalFilter(IN in, OUT out) {

    }

    @Override
    protected void afterFilter(IN in, OUT out) {

    }

    @Override
    protected void beforeFilter(IN in, OUT out) {

    }
}
