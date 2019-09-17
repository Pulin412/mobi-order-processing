package com.mobi.order.mobiOrderService.controller;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderServiceController {

    @Autowired
     OrderServiceImpl orderService;

    @RequestMapping("/getOrder/{orderId}")
    public OrderDetails getOrder(@PathVariable  Long orderId){

        return  orderService.getOrder(orderId);
    }

}
