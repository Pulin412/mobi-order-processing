package com.mobi.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The type Mobi inventory service application.
 */
@SpringBootApplication
@EnableEurekaClient
public class MobiInventoryServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(MobiInventoryServiceApplication.class, args);
    }

}
