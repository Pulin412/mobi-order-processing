package com.mobi.inventory.controller;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/")
    public ResponseDto getHome() {
        return new ResponseDto("Home page", HttpStatus.OK.toString(), null);
    }

    @GetMapping("/initDB")
    public ResponseDto initDatabase() {
        return inventoryService.initializeDB();
    }

    @PostMapping("/addProduct")
    public ResponseDto addProduct(@RequestBody ProductDto productDto) {
        return inventoryService.addProduct(productDto);
    }

    @PostMapping("/addProductList")
    public ResponseDto addProductList(@RequestBody List<ProductDto> productDtoList) {
        return inventoryService.addListOfProducts(productDtoList);
    }

    @PostMapping("/removeProduct/{id}")
    public ResponseDto removeProduct(@PathVariable String id) {
        return inventoryService.removeProduct(id);
    }

    @GetMapping("/deleteAllProducts")
    public ResponseDto deleteAllProducts() {
        return inventoryService.removeAllProducts();
    }

    @GetMapping("/getAllProducts")
    public ResponseDto getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseDto findById(@PathVariable String id) {
        return inventoryService.getProductById(id);
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseDto findByName(@PathVariable String name) {
        return inventoryService.getProductByName(name);
    }

    @PostMapping("/updateProduct")
    public ResponseDto updateProduct(@RequestBody ProductDto productDto) {
        return inventoryService.updateProduct(productDto);
    }
}
