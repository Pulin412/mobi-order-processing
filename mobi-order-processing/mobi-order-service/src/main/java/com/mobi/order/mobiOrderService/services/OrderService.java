package com.mobi.order.mobiOrderService.services;

import com.mobi.order.mobiOrderService.controller.ProductClient;
import com.mobi.order.mobiOrderService.dto.OrderDto;
import com.mobi.order.mobiOrderService.dto.OrderResponseDto;
import com.mobi.order.mobiOrderService.dto.ProductDto;
import com.mobi.order.mobiOrderService.dto.ResponseDto;
import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
import com.mobi.order.mobiOrderService.util.OrderStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    OrderRepository orderRepository;
    ModelMapper modelMapper;
    ProductClient productClient;
    ProductDto productDto;
    OrderResponseDto orderResponseDto = new OrderResponseDto();

    @Autowired
    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper, ProductClient productClient) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.productClient = productClient;
    }

    public OrderDetails getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    public OrderResponseDto placeOrder(OrderDto orderDto) {
        updateInventories(orderDto);
        return orderResponseDto;
    }

    public ResponseDto getInventory() {
        return productClient.findAll();
    }

    private OrderResponseDto updateInventories(OrderDto orderDto) {
        Map<Long, Integer> productQuantityMap = orderDto.getProductQuantityMap();
        for (Map.Entry<Long, Integer> eachProduct : productQuantityMap.entrySet()) {
            ResponseDto productById = productClient.findProductById(eachProduct.getKey().toString());
            if (productById.getProductDtoList() != null && !productById.getProductDtoList().isEmpty() ) {
                if (eachProduct.getValue() <= productById.getProductDtoList().get(0).getQuantity()) {
                    productDto = productById.getProductDtoList().get(0);
                    productDto.setQuantity(productDto.getQuantity() - eachProduct.getValue());
                    productClient.updateProduct(productDto);
                    orderResponseDto.setMessage("Order Successfully placed");
                    orderResponseDto.setStatus(OrderStatus.Placed);
                    OrderDetails orderDetails = orderRepository.save(orderDtoToOrderEntity(orderDto));
                    orderResponseDto.setOrderDto(orderEntityToOrderDto(orderDetails));
                } else {
                    orderResponseDto.setMessage("Not enough quantity in stock");
                    orderResponseDto.setStatus(OrderStatus.Cancelled);
                }
            } else {
                orderResponseDto.setMessage("No matching products found.");
                orderResponseDto.setStatus(OrderStatus.Cancelled);
            }
        }
        return orderResponseDto;
    }

    private OrderDto convertToDto(OrderDetails orderDetails) {
        return modelMapper.map(orderDetails, OrderDto.class);
    }

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

    public String deleteOrder(Long orderId) {
        if (orderRepository.findByOrderId(orderId) != null) {
            orderRepository.deleteById(orderId);
            return "Order deleted successfully";
        } else {
            return "ObjectId not found";
        }
    }

    public List<OrderDetails> getOrders() {
        return orderRepository.findAll();
    }
}
