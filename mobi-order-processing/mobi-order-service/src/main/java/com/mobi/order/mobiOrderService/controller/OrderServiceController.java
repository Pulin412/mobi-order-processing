package com.mobi.order.mobiOrderService.controller;


import com.mobi.order.mobiOrderService.dto.OrderDto;
import com.mobi.order.mobiOrderService.dto.OrderResponseDto;
import com.mobi.order.mobiOrderService.dto.ResponseDto;
import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
import com.mobi.order.mobiOrderService.services.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order/api/v1")
@RestController
public class OrderServiceController {

    OrderDto orderDto;
    OrderResponseDto orderResponseDto;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @ApiOperation("this is the end point to get an order from the records ")
    @GetMapping("/{orderId}")
    public OrderDetails getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/test")
    public ResponseDto testProduct() {
        return orderService.getInventory();
    }

    @PostMapping("/placeOrder")
    public @ResponseBody
    OrderResponseDto placeOrder(@RequestBody OrderDto orderDto) {
        return orderService.placeOrder(orderDto);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public String deleteOrder(@PathVariable Long orderId) {
        return orderService.deleteOrder(orderId);
    }

    @GetMapping("/orders")
    public List<OrderDetails> getOrders() {
        return orderService.getOrders();
    }
}
