package com.mobi.order.mobiOrderService.controller;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.order.mobiOrderService.dto.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventory-service")//product search
public interface ProductClient {

    @GetMapping("/inventory/api/v1/")
    public ResponseDto findAll();

    @GetMapping("/inventory/api/v1/product/{id}")
    public ResponseDto findProductById(@PathVariable String id);

    @PutMapping("/inventory/api/v1/")
    public ResponseDto updateProduct(@RequestBody ProductDto productDto);
}
