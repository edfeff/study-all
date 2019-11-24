package com.wpp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/p2" )
public class P2Controller {
    @RequestMapping( "/m2" )
    public String m1() {
        return "m2";
    }
}
