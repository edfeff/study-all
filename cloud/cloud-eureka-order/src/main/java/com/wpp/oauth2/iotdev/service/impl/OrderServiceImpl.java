package com.wpp.oauth2.iotdev.service.impl;

import com.wpp.oauth2.iotdev.common.Order;
import com.wpp.oauth2.iotdev.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @author wangpp
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> orderList() {
        return Arrays.asList(new Order("order_1", "naichai"));
    }
}
