package com.wpp.feign;

import com.wpp.base.order.OrderResponse;
import com.wpp.base.order.to.OrderParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author wangpp
 */
@FeignClient( "CLOUD-EUREKA-ORDER" )
public interface OrderClient {

    @GetMapping( "/order/all" )
    OrderResponse getAll();

    @GetMapping( "/order/{id}" )
    public OrderResponse getById(@PathVariable( value = "id", required = true ) String id);

    @GetMapping( "/order/name" )
    public OrderResponse getByParam(@RequestParam( value = "name" ) String name);

    @GetMapping( "/order/orderParam" )
    public OrderResponse get(@RequestBody OrderParam orderParam);

    @GetMapping( "/order/map" )
    public OrderResponse get(@RequestParam Map<String, Object> params);

}
