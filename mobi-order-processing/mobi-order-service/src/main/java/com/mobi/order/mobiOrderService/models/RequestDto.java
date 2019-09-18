package com.mobi.order.mobiOrderService.models;

import com.mobi.order.mobiOrderService.entities.Order;
import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    List<Order> orderList;
}
