package com.mobi.inventory.controller;

import com.mobi.inventory.entity.Product;
import com.mobi.inventory.service.InventoryService;
import com.mobi.inventory.utils.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/")
    public String getHome() {
        return "Home Page";
    }

    @GetMapping("/initDB")
    public String initDatabase() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("T Shirt", "Cotton shirt", 500.0, 800.0, 15.0, Category.FASHION.getKey()));
        productList.add(new Product("iphone 11", "Smart phone", 100000.0, 120000.0, 11.0, Category.ELECTRONICS.getKey()));
        productList.add(new Product("OnePlus 7T", "Smart phone", 50000.0, 56000.0, 4.0, Category.ELECTRONICS.getKey()));
        productList.add(new Product("Craft Papers", "A4 craft papers", 150000.0, 170000.0, 5.0, Category.ARTS.getKey()));
        productList.add(new Product("Alienware", "Dell gaming laptop", 500.0, 800.0, 7.0, Category.COMPUTERS.getKey()));
        productList.add(new Product("T Shirt", "Cotton Shirt", 500.0, 800.0, 15.0, Category.FASHION.getKey()));
        productList.add(new Product("Protein Powder", "5lb whey protein", 6000.0, 8000.0, 23.0, Category.HEALTH.getKey()));
        productList.add(new Product("Car cover", "Car protective cover for SUV", 2500.0, 2800.0, 20.0, Category.AUTOMOTIVE.getKey()));
        inventoryService.addListOfProducts(productList);
        return "Added Products Successfully";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Product product) {
        product.setCategory(Category.valueOf(product.getCategory()).getKey());
        inventoryService.addProduct(product);
        return "Product Added Successfully";
    }

    @PostMapping("/removeProduct")
    public String removeProduct(@RequestBody Product product) {
        inventoryService.removeProduct(product);
        return "Product Removed Successfully";
    }

    @GetMapping("/deleteAllProducts")
    public List<Product> deleteAllProducts() {
        return inventoryService.getAllProducts();
    }

    @GetMapping("/getAllProducts")
    public List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    @GetMapping(value = "/findByName/{name}")
    public List<Product> findByName(@PathVariable String name) {
        return inventoryService.getProductByName(name);
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestBody Product product) {
        inventoryService.removeProduct(product);
        return "Product Removed Successfully";
    }
}
