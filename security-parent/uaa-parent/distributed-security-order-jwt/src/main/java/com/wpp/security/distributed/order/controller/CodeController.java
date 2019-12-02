package com.wpp.security.distributed.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
public class CodeController {
    @RequestMapping( "/oauth2" )
    public String code(@RequestParam( value = "code", required = false ) String code) {
        return code;
    }
}
