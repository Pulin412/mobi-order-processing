package com.mobi.order.mobiOrderService.services.impl;

import com.mobi.order.mobiOrderService.entities.Order;
import com.mobi.order.mobiOrderService.models.RequestDto;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
//import com.mobi.order.mobiOrderService.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

//    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

//    @Override
    public Long placeOrder(RequestDto requestDto) {
        return null;
    }

//    @Override
    public String getOrderStatus(Long orderId) {
        return null;
    }
}
