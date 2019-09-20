package com.mobi.order.mobiOrderService.services;

import com.mobi.order.mobiOrderService.dto.ResponseDto;
import com.mobi.order.mobiOrderService.entities.Order;
import com.mobi.order.mobiOrderService.dto.OrderDto;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    public ResponseDto getOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
        if (order.isPresent()) {
            List<Order> orderList = new ArrayList<>();
            orderList.add(order.get());
            responseDto.setMessage("Found the order");
            responseDto.setOrderDtoList(orderListToOrderDtoList(orderList));
        } else {
            responseDto.setMessage("Order not found");
        }
        return responseDto;
    }

    public ResponseDto placeOrder(List<OrderDto> orderDtoList) {
        List<Order> orders = orderRepository.saveAll(orderDtoListToOrderList(orderDtoList));
        return new ResponseDto("Order placed", HttpStatus.OK.toString(), orderListToOrderDtoList(orders));
    }

    public String getOrderStatus(Long orderId) {
        return null;
    }

    private List<OrderDto> orderListToOrderDtoList(List<Order> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orderList) {
            orderDtoList.add(convertToDto(order));
        }
        return orderDtoList;
    }

    private List<Order> orderDtoListToOrderList(List<OrderDto> orderDtoList) {
        List<Order> orderList = new ArrayList<>();
        for (OrderDto orderDto : orderDtoList) {
            orderList.add(convertToEntity(orderDto));
        }
        return orderList;
    }

    private OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    private Order convertToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
