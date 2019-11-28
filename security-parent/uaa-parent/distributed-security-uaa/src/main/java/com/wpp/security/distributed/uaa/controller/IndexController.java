package com.wpp.security.distributed.uaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/" )
public class IndexController {
    @GetMapping( produces = "text/plain;charset=utf-8;" )
    public String hello() {
        return "hello-world";
    }
}
