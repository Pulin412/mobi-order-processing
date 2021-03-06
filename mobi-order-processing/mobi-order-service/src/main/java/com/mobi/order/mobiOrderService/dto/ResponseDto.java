package com.mobi.order.mobiOrderService.dto;

import com.mobi.demo.OrderDto;
import com.mobi.demo.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    String message;
    String status;
    OrderDto orderDto;
    List<ProductDto> productDtoList;
    List<OrderDto> orderDtoList;
}