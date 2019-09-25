package com.mobi.order.mobiOrderService.dto;

import com.mobi.order.mobiOrderService.util.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDto {
    String message;
    OrderStatus status;
    OrderDto orderDto;
}