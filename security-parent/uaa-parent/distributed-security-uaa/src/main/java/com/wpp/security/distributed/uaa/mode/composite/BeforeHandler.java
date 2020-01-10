package com.wpp.security.distributed.uaa.mode.composite;

public class BeforeHandler implements IHandler {
    @Override
    public void handle(Object request, Object response, Object context) {
        System.out.println("before");
    }
}
