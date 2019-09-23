package com.mobi.order.mobiOrderService.controller;


import com.mobi.order.mobiOrderService.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="inventory-service" )//product search
public interface ProductClient {
    @GetMapping("/inventory/api/v1/")
    public ResponseDto findAll();
}
