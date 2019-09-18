package com.mobi.order.mobiOrderService.repository;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetails, Long> {

    OrderDetails findByOrderId(long orderId);
}
