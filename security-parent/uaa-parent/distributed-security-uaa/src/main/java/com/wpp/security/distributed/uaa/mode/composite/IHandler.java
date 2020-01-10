package com.wpp.security.distributed.uaa.mode.composite;

/**
 * 复合代理模式
 */
public interface IHandler {
    /**
     * 数据处理接口
     *
     * @param request
     * @param response
     * @param context
     * @return
     */
    void handle(Object request, Object response, Object context);
}
