package com.mobi.order.mobiOrderService;

import com.mobi.order.mobiOrderService.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

//@ComponentScan(basePackages = "com.mobi.order.mobiOrderService.controller, com.mobi.order.mobiOrderService.service, com.mobi.order.mobiOrderService.services.impl.OrderService")
@SpringBootApplication
@EnableEurekaClient
public class MobiOrderServiceApplication {

    @Autowired
    static OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(MobiOrderServiceApplication.class, args);

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
