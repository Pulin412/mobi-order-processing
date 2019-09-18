package com.mobi.order.mobiOrderService.controller;

import com.mobi.order.mobiOrderService.entities.Order;
import com.mobi.order.mobiOrderService.models.RequestDto;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
import com.mobi.order.mobiOrderService.services.impl.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class OrderServiceController {

    RequestDto requestDto;

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/test")
    public String testMessage() {
        Order o1 = new Order();
        o1.setOrderStatus("complete");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        o1.setOrderTime(date);
        Map<String, Integer> map = new HashMap<>();
        map.put("Pen", 10);
        o1.setProductQtyMap(map);
        if (o1 != null)
            orderRepository.save(o1);
        return "Added successfully";
    }

    @RequestMapping("/getOrder/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderServiceImpl.getOrder(orderId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/placeOrder")
    public Long placeOrder(RequestDto requestDto) {
        return orderServiceImpl.placeOrder(requestDto);
    }

    @RequestMapping("/getOrderStatus/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId) {
        return orderServiceImpl.getOrderStatus(orderId);
    }
}
