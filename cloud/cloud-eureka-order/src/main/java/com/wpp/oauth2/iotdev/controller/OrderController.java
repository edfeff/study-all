package com.wpp.oauth2.iotdev.controller;

import com.wpp.oauth2.iotdev.common.Order;
import com.wpp.oauth2.iotdev.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    public List<Order> orderList(HttpServletRequest request) {
        int localPort = request.getLocalPort();
        List<Order> orders = orderService.orderList();
        orders.get(0).setName("服务端口" + localPort);
        return orders;
    }
}
