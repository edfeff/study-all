package com.wpp.security.distributed.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/index" )
public class IndexController {
    @GetMapping( produces = "text/plain;charset=utf-8;" )
    public String hello() {
        return "hello-world";
    }

    @GetMapping( value = "/p1", produces = "text/plain;charset=utf-8;" )
//    @PreAuthorize( value = "hasAuthority('p1')" )
    public String hello1() {
        return "order权限资源 -p1";
    }
}
