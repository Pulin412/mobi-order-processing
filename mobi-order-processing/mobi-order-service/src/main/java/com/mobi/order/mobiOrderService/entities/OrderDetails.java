package com.mobi.order.mobiOrderService.entities;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class OrderDetails {

    Long id;
    Long orderId;
    String orderStatus;
    Date orderTime;



}
