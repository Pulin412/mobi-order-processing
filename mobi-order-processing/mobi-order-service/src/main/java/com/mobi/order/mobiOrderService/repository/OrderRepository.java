package com.mobi.order.mobiOrderService.repository;

import com.mobi.order.mobiOrderService.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
