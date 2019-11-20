package com.wpp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wangpp
 */
@Controller
@RequestMapping( "/product" )
public class ProductController {
    @RequestMapping( "/index" )
    public String index() {
        return "index";
    }

    @RequestMapping( "/add" )
    public String add() {
        return "product/productAdd";
    }

    @RequestMapping( "/delete" )
    public String delete() {
        return "product/productDelete";
    }

    @RequestMapping( "/update" )
    public String update() {
        return "product/productUpdate";
    }

    @RequestMapping( "/list" )
    public String list() {
        return "product/productList";
    }
}
