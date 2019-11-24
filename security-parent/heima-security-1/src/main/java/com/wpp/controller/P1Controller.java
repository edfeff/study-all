package com.wpp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/p1" )
public class P1Controller {
    @RequestMapping( "/m1" )
    public String m1() {
        return "m1";
    }
}
