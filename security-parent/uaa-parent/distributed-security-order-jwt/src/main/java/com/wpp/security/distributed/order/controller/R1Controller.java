package com.wpp.security.distributed.order.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/r1" )
public class R1Controller {
    @RequestMapping( value = "/p1", produces = "text/plain;charset=utf-8;" )
    @PreAuthorize( value = "hasAnyAuthority('p1')" )
    public String hello1() {
        return "order权限资源 -p1";
    }

    @RequestMapping( value = "/p2", produces = "text/plain;charset=utf-8;" )
    @PreAuthorize( value = "hasAnyAuthority('p2')" )
    public String hello2() {
        return "order权限资源 -p2";
    }

    @RequestMapping( value = "/p3", produces = "text/plain;charset=utf-8;" )
    @PreAuthorize( value = "hasAnyAuthority('p3')" )
    public String hello3() {
        return "order权限资源 -p3";
    }
}
