package com.mobi.order.mobiOrderService.services;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.models.PlaceOrderReq;
import com.mobi.order.mobiOrderService.models.Product;

import java.util.List;

public interface OrderService {

    OrderDetails getOrder(Long orderId);
    Long placeOrder(PlaceOrderReq placeOrderReq);
    String getOrderStatus(Long orderId);

}
