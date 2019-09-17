package com.mobi.inventory.service;

import com.mobi.inventory.entity.Product;
import com.mobi.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    InventoryRepository inventoryRepository;

    public Optional<Product> getProductById(Long productId) {
        return inventoryRepository.findById(productId);
    }

    public List<Product> getProductByName(String name) {
        return inventoryRepository.findByName(name);
    }

    public void addProduct(Product product) {
        inventoryRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return inventoryRepository.findAll();
    }

    public List<Product> addListOfProducts(List<Product> productList) {
        return inventoryRepository.saveAll(productList);
    }

    public void removeProduct(Product product) {
        inventoryRepository.delete(product);
    }

    public void removeAllProducts() {
        inventoryRepository.deleteAll();
    }

    public void updateProduct(Product product) {
        Optional<Product> tempProduct = inventoryRepository.findById(product.getProductId());
        if (tempProduct.isPresent()) {
            inventoryRepository.delete(tempProduct.get());
            inventoryRepository.save(product);
        }
    }

}
