package com.wpp.security.distributed.uaa.mode.composite;

import java.util.Arrays;
import java.util.List;

/**
 * 复合处理类
 * <p>
 * 用于组合多个处理类
 * <p>
 * 参考实现 Spring Security LogoutHandler 与 CompositeLogoutHandler
 *
 * @author wpp
 */
public class CompositeHandler implements IHandler {
    private final List<IHandler> handlerList;

    public CompositeHandler(List<IHandler> handlerList) {
        assert (handlerList == null);
        this.handlerList = handlerList;
    }

    public CompositeHandler(IHandler... handlers) {
        assert (handlers == null);
        this.handlerList = Arrays.asList(handlers);
    }

    @Override
    public void handle(Object request, Object response, Object context) {
        if (handlerList != null) {
            //TODO 可以加入排序功能
            for (IHandler handler : handlerList) {
                handler.handle(request, response, context);
            }
        }
    }
}
