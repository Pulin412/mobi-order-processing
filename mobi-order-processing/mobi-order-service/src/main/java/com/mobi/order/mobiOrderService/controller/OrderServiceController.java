package com.mobi.order.mobiOrderService.controller;

import com.mobi.order.mobiOrderService.entities.Order;
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

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/test")
    public String testMessage() {
        Order o1 = new Order();
        o1.setOrderStatus("complete");
        Date date = new Date();
        o1.setOrderTime(date);
        Map<String, Integer> map = new HashMap<>();
        map.put("Pen", 10);
        o1.setProductQtyMap(map);
        if (o1 != null)
            orderRepository.save(o1);
        return "Added successfully";
    }

    @RequestMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/placeOrder")
    public Long placeOrder(OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @RequestMapping("/getOrderStatus/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId) {
        return orderService.getOrderStatus(orderId);
    }
}
