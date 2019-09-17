package com.mobi.order.mobiOrderService.services.impl;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.models.PlaceOrderReq;
import com.mobi.order.mobiOrderService.models.Product;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
import com.mobi.order.mobiOrderService.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PlaceOrderReq placeOrderReq;

    @Override
    public OrderDetails getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    @Override
    public Long placeOrder(PlaceOrderReq placeOrderReq) {
        return null;
    }

    @Override
    public String getOrderStatus(Long orderId) {
        return null;
    }
}
