package com.mobi.order.mobiOrderService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackages = "com.mobi.order.mobiOrderService.controller, com.mobi.order.mobiOrderService.service, com.mobi.order.mobiOrderService.services.impl.OrderServiceImpl")
@SpringBootApplication
public class MobiOrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobiOrderServiceApplication.class, args);
	}

}
