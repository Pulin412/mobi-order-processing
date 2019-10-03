package com.mobi.inventory.controller;

import com.mobi.inventory.dto.ProductDto;
import com.mobi.inventory.dto.ResponseDto;
import com.mobi.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Inventory controller.
 */
@RequestMapping("inventory/api/v1")
@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * Gets all products.
     *
     * @return the all products
     */
    @GetMapping("/")
    public ResponseDto getAllProducts() {
        return inventoryService.getAllProducts();
    }

    /**
     * Find by id response dto.
     *
     * @param id the id
     * @return the response dto
     */
    @GetMapping(value = "/product/{id}")
    public ResponseDto findById(@PathVariable Long id) {
        return inventoryService.getProductById(id);
    }

    /**
     * Find by name response dto.
     *
     * @param name the name
     * @return the response dto
     */
    @GetMapping(value = "/productByName/{name}")
    public ResponseDto findByName(@PathVariable String name) {
        return inventoryService.getProductByName(name);
    }

    /**
     * Add products response dto.
     *
     * @param productDtoList the product dto list
     * @return the response dto
     */
    @PostMapping("/")
    public ResponseDto addProducts(@RequestBody List<ProductDto> productDtoList) {
        return inventoryService.addListOfProducts(productDtoList);
    }

    /**
     * Remove product response dto.
     *
     * @param id the id
     * @return the response dto
     */
    @DeleteMapping("/{id}")
    public ResponseDto removeProduct(@PathVariable Long id) {
        return inventoryService.removeProduct(id);
    }

    /**
     * Delete all products response dto.
     *
     * @return the response dto
     */
    @DeleteMapping("/")
    public ResponseDto deleteAllProducts() {
        return inventoryService.removeAllProducts();
    }

    /**
     * Update product response dto.
     *
     * @param productDto the product dto
     * @return the response dto
     */
    @PutMapping("/")
    public ResponseDto updateProduct(@RequestBody ProductDto productDto) {
        return inventoryService.updateProduct(productDto);
    }
}
