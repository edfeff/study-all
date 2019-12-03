package com.wpp.security.distributed.uaa.mode.filter;

public abstract class AbstractWorkFilter<IN, OUT> implements WorkFilter<IN, OUT> {

    @Override
    public void doFilter(IN in, OUT out, WorkFilterChain chain) throws Exception {
        try {
            beforeFilter(in, out);
            chain.doFilter(in, out);
            afterFilter(in, out);
        } catch (Exception e) {
            exceptionFilter(in, out, e);
        } finally {
            finalFilter(in, out);
        }
    }

    protected abstract void exceptionFilter(IN in, OUT out, Exception e);

    protected abstract void finalFilter(IN in, OUT out);

    protected abstract void afterFilter(IN in, OUT out);

    protected abstract void beforeFilter(IN in, OUT out);
}
