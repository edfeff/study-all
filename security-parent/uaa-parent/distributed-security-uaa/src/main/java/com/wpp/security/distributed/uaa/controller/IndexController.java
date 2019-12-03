package com.wpp.security.distributed.uaa.controller;

import com.wpp.security.distributed.uaa.common.User;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
@RequestMapping("/")
public class IndexController {
    @GetMapping(produces = "text/plain;charset=utf-8;")
    public String hello() {
        return "hello-world";
    }

    @GetMapping(value = "/home", produces = "text/plain;charset=utf-8;")
    public String home(Authentication authentication) {
        if (authentication != null) {
            User principal = (User) authentication.getPrincipal();
            return principal.getRealName();
        }
        return "未登录";
    }
}
