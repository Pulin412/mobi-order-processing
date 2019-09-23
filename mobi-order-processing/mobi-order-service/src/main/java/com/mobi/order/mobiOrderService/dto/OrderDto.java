package com.mobi.order.mobiOrderService.dto;


import lombok.Data;

import java.util.Map;

@Data
public class OrderDto {
    long orderId;
    long customerId;
    Map<Long, Integer> productQuantityMap;
}
