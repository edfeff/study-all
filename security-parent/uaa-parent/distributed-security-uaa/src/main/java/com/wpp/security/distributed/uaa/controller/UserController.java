package com.wpp.security.distributed.uaa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, String> user(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> map = new HashMap<>();
        map.put("username", "name");
        while (headerNames.hasMoreElements()) {
            String s = headerNames.nextElement();
            System.out.println(s + " -- " + request.getHeader(s));
            map.put(s, request.getHeader(s));
        }
        return map;
    }
}
