package com.mobi.order.mobiOrderService.models;

import com.mobi.order.mobiOrderService.entities.Order;

import java.util.List;

public class ResponseDto {
    String message;
    String status;
    List<Order> orderList;
}