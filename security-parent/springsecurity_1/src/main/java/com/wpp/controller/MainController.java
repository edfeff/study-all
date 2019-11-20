package com.wpp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangpp
 */
@Controller
public class MainController {
    @RequestMapping( "/userLogin" )
    public String login() {
        return "login";
    }

    @RequestMapping( "/error" )
    public String error() {
        return "error";
    }
}
