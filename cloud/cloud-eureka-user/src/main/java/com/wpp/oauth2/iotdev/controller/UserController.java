package com.wpp.oauth2.iotdev.controller;

import com.wpp.base.ResponseData;
import com.wpp.base.order.OrderResponse;
import com.wpp.base.order.to.OrderParam;
import com.wpp.feign.OrderClient;
import com.wpp.oauth2.iotdev.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpp
 */
@RestController
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    UserService userService;

//    @Autowired
//    OrderFeignClient orderFeignClient;

    @Autowired
    OrderClient orderFeignClient;

    @GetMapping
    public ResponseData userList() {
        OrderResponse allOrder = orderFeignClient.getAll();
        if (allOrder == null) {
            return new ResponseData("该用户没有订单");
        }
        ResponseData responseData = new ResponseData(allOrder);
        return responseData;
    }

    @GetMapping( "/orderParam" )
    public OrderResponse orderParam() {
        OrderParam orderParam = new OrderParam();
        orderParam.setId("id_1");
        orderParam.setOrderCode("code_1");
        OrderResponse orderResponse = orderFeignClient.get(orderParam);
        return orderResponse;
    }

    @GetMapping( "/name" )
    public OrderResponse name() {
        OrderResponse wpp = orderFeignClient.getByParam("wpp");
        return wpp;
    }

    @GetMapping( "/map" )
    public OrderResponse map() {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "wpp");
        params.put("age", 27);
        OrderResponse orderResponse = orderFeignClient.get(params);
        return orderResponse;
    }

}
