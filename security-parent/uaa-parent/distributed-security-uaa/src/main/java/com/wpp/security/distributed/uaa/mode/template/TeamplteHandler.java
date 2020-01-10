package com.wpp.security.distributed.uaa.mode.template;

import com.wpp.security.distributed.uaa.mode.composite.IHandler;

/**
 * 模板方法
 *
 * @author wpp
 */
public class TeamplteHandler {
    private HandlerMatcher handlerMatcher;
    private SuccessHandler successHandler;
    private FailHandler failHandler;
    private IHandler handler;

    public TeamplteHandler() {
    }

    /**
     * 模板处理方法
     *
     * @param object
     */
    public void templateHandle(Object object) {
        //是否应该处理
        if (shouldHandler(object)) {
//            处理
            if (internalHandle(object)) {
//            处理成功
                successHandle(object);
            } else {
                //处理失败
                failHandle(object);
            }
        } else {
            //TODO 交给其他处理器 此处不做处理
        }
    }

    protected void failHandle(Object object) {
        failHandler.onFail(object);
    }

    protected void successHandle(Object object) {
        successHandler.onSuccess(object);
    }

    protected boolean internalHandle(Object object) {
        try {
            //这里可以使用复杂的Composite模式处理
            handler.handle(object, null, null);
        } catch (Exception e) {
            //TODO 日志记录 或者 错误处理
            return false;
        }
        return true;
    }

    protected boolean shouldHandler(Object object) {
        return handlerMatcher.match(object);
    }

    public HandlerMatcher getHandlerMatcher() {
        return handlerMatcher;
    }

    public void setHandlerMatcher(HandlerMatcher handlerMatcher) {
        this.handlerMatcher = handlerMatcher;
    }

    public SuccessHandler getSuccessHandler() {
        return successHandler;
    }

    public void setSuccessHandler(SuccessHandler successHandler) {
        this.successHandler = successHandler;
    }

    public FailHandler getFailHandler() {
        return failHandler;
    }

    public void setFailHandler(FailHandler failHandler) {
        this.failHandler = failHandler;
    }

    public IHandler getHandler() {
        return handler;
    }

    public void setHandler(IHandler handler) {
        this.handler = handler;
    }
}
