package com.mobi.order.mobiOrderService.controller;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.models.PlaceOrderReqDto;
import com.mobi.order.mobiOrderService.services.impl.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceController {

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @RequestMapping("/getOrder/{orderId}")
    public OrderDetails getOrder(@PathVariable  Long orderId){
        return  orderServiceImpl.getOrder(orderId);
    }

    @RequestMapping(method= RequestMethod.POST, value = "/placeOrder")
    public Long placeOrder(PlaceOrderReqDto placeOrderReqDto){
        return orderServiceImpl.placeOrder(placeOrderReqDto);
    }

    @RequestMapping("/getOrderStatus/{orderId}")
    public String getOrderStatus(@PathVariable  Long orderId){
        return  orderServiceImpl.getOrderStatus(orderId);
    }
}
