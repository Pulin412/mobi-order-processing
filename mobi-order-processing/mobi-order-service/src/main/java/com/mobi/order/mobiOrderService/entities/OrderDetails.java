package com.mobi.order.mobiOrderService.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.Map;

@Entity
@Data
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private Long orderId;
    @Column(name="order_status")
    private String orderStatus;
    @Column(name="order_time")
    private Date orderTime;

    @ElementCollection
    private Map<String, Integer> productQtyMap;
}