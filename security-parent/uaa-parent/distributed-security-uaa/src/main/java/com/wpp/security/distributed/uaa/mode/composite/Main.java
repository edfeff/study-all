package com.wpp.security.distributed.uaa.mode.composite;

public class Main {
    public static void main(String[] args) {
        IHandler handler = new CompositeHandler(new BeforeHandler(), new AfterHandler());
        handler.handle(null, null, null);
    }
}
