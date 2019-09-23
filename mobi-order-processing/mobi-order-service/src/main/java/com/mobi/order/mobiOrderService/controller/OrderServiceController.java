package com.mobi.order.mobiOrderService.controller;

import com.mobi.order.mobiOrderService.dto.OrderResponseDto;
import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.dto.OrderDto;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
import com.mobi.order.mobiOrderService.services.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderServiceController {

    OrderDto orderDto;
    OrderResponseDto orderResponseDto;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/{orderId}")
    public OrderDetails getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PostMapping("/placeOrder")
    public @ResponseBody  OrderResponseDto placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @GetMapping("/deleteOrder/{orderId}")
    public void deleteOrder (@PathVariable Long orderId) {

    }
}
