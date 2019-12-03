package com.wpp.oauth2.iotdev.controller;

import com.wpp.oauth2.iotdev.common.Order;
import com.wpp.oauth2.iotdev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/list" )
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> orderList() {
        return orderService.orderList();
    }

}
