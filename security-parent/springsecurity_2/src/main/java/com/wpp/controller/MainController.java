package com.wpp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping({"/", "index", "index.html", "index.jsp"})
    public String index() {
        return "index";
    }
}
