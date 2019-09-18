package com.mobi.order.mobiOrderService.services.impl;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.models.PlaceOrderReqDto;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
//import com.mobi.order.mobiOrderService.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

    PlaceOrderReqDto placeOrderReqDto;

//    @Override
    public OrderDetails getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

//    @Override
    public Long placeOrder(PlaceOrderReqDto placeOrderReqDto) {
        return null;
    }

//    @Override
    public String getOrderStatus(Long orderId) {
        return null;
    }
}
