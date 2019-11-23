package com.wpp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping( {"/", "index", "index.html", "index.jsp"} )
    public String index() {
        return "index";
    }

    @RequestMapping( {"/userLogin", "/login.jsp", "/login.html"} )
    public String login() {
        return "login";
    }

    @RequestMapping( {"/error", "/error.jsp", "/error.html"} )
    public String error() {
        return "error";
    }
}
