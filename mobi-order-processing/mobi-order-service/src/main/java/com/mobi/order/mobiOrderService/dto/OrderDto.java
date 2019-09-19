package com.mobi.order.mobiOrderService.dto;


import com.mobi.inventory.dto.ProductDto;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    long orderId;
    long customerId;
    List<ProductDto> products;
}
