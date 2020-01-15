package com.wpp.oauth2.iotdev.util;


import com.wpp.base.order.Order;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangpp
 */
public class DataCenter {
    public static final Map<String, Order> orderMap = new HashMap<>();

    static {
        for (int i = 0; i < 10; i++) {
            Order order = new Order("o-" + i, "Order-" + i);
            orderMap.put(order.getId(), order);
        }
    }

    private DataCenter() {
    }

    public static void create(String name, Order order) {
        if (orderMap.get(name) != null) {
            return;
        }
        orderMap.put(name, order);
    }

    public static void update(String name, Order order) {
        if (orderMap.get(name) == null) {
            return;
        }
        orderMap.put(name, order);
    }

    public static boolean delete(String name) {
        Order remove = orderMap.remove(name);
        if (remove == null) {
            return false;
        }
        return true;
    }

    public static Order get(String name) {
        return orderMap.get(name);
    }

    public static Collection<Order> getAll() {
        return orderMap.values();
    }
}
