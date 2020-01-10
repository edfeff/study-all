package com.wpp.oauth2.iotdev.feign.order;

import com.wpp.base.order.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wangpp
 */
@FeignClient( value = "${cloud.service.user.name}" )
public interface OrderFeignClient {
    @GetMapping( "/order/all" )
    public OrderResponse getAll();

    @GetMapping( "/order/{orderId}" )
    public OrderResponse getOrder(@PathVariable( value = "orderId" ) String orderId);
}
