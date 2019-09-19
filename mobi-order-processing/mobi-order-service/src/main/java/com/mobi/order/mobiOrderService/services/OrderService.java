package com.mobi.order.mobiOrderService.services;

import com.mobi.order.mobiOrderService.entities.Order;
import com.mobi.order.mobiOrderService.dto.OrderDto;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
//import com.mobi.order.mobiOrderService.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

//    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

//    @Override
    public Long placeOrder(OrderDto orderDto) {
        return null;
    }

//    @Override
    public String getOrderStatus(Long orderId) {
        return null;
    }
}
