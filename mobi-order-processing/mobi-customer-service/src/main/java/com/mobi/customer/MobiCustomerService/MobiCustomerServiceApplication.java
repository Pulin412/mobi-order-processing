package com.mobi.customer.MobiCustomerService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MobiCustomerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(MobiCustomerServiceApplication.class, args);
    }
}
