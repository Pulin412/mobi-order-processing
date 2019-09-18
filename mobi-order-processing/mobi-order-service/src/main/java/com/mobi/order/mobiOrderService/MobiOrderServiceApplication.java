package com.mobi.order.mobiOrderService;

import com.mobi.order.mobiOrderService.entities.OrderDetails;
import com.mobi.order.mobiOrderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@ComponentScan(basePackages = "com.mobi.order.mobiOrderService.controller, com.mobi.order.mobiOrderService.service, com.mobi.order.mobiOrderService.services.impl.OrderServiceImpl")
@SpringBootApplication
public class MobiOrderServiceApplication {

    @Autowired
    static OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(MobiOrderServiceApplication.class, args);

    }
}
