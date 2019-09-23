package com.mobi.order.mobiOrderService.services;

import com.mobi.order.mobiOrderService.dto.OrderResponseDto;
import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.dto.OrderDto;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
//import com.mobi.order.mobiOrderService.services.OrderService;
import com.mobi.order.mobiOrderService.util.OrderStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    //    @Override
    public OrderDetails getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    //    @Override
    public OrderResponseDto placeOrder(OrderDto orderDto) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        OrderDetails orderDetails = orderRepository.save(orderDtoToOrderEntity(orderDto));
        orderResponseDto.setOrderDto(orderEntityToOrderDto(orderDetails));
        orderResponseDto.setMessage("Order Successfully placed");
        orderResponseDto.setStatus(OrderStatus.Placed);
        orderResponseDto.setOrderDto(orderEntityToOrderDto(orderDetails));
        return orderResponseDto;
    }


    private OrderDto convertToDto(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDto.class);
    }

//    private OrderDetails convertToEntity(OrderDto orderDto) {
//        return modelMapper.map(orderDto, OrderDetails.class);
//    }


    private OrderDto orderEntityToOrderDto(OrderDetails orderDetails) {
        return convertToDto(orderDetails);
    }

    private OrderDetails orderDtoToOrderEntity(OrderDto orderDto) {
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderStatus(OrderStatus.Placed.toString());
        Date date = new Date();
        orderDetails.setOrderTime(date);
        orderDetails.setCustomerId(orderDto.getCustomerId());
        orderDetails.setProductQuantityMap(orderDto.getProductQuantityMap());
        return orderDetails;
    }

    public OrderResponseDto getOrderStatus(OrderResponseDto orderResponseDto) {

        return orderResponseDto;
    }
}
