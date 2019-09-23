package com.mobi.order.mobiOrderService.dto;

import com.mobi.order.mobiOrderService.dto.OrderDto;
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
}