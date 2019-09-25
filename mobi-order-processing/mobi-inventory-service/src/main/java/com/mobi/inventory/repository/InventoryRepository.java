package com.mobi.inventory.repository;

import com.mobi.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * The interface Inventory repository.
 */
public interface InventoryRepository extends JpaRepository<Product, Long> {

    /**
     * Find by name list.
     *
     * @param name the name
     * @return the list
     */
    List<Product> findByName(String name);

}
