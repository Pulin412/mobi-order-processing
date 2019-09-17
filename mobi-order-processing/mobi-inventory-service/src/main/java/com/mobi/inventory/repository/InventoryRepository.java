package com.mobi.inventory.repository;

import com.mobi.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

}
