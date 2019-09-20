package com.mobi.order.mobiOrderService.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderDto {
    long orderId;
    long customerId;
    Map<Product, Integer> productQtyMap;
}
