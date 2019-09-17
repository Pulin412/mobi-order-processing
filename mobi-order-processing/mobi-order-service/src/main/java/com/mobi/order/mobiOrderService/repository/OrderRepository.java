package com.mobi.order.mobiOrderService.repository;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Long, OrderDetails> {
    OrderDetails findByOrderId(long orderId);
}
