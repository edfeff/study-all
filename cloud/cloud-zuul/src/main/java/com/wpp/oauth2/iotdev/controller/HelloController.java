package com.wpp.oauth2.iotdev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangpp
 */
@RestController
public class HelloController {
    @GetMapping( "/local/{key}" )
    public String key(@PathVariable String key) {
        return "local:" + key;
    }
}
