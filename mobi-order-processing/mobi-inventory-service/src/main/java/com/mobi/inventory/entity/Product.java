package com.mobi.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "PRODUCT")
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "base_price")
    Double basePrice;

    @Column(name = "mrp")
    Double mrp;

    @Column(name = "tax")
    Double tax;

    @Column(name = "category")
    String category;

    @Column(name = "quantity")
    Integer quantity = 0;

    public Product(String name, String description, Double basePrice, Double mrp, Double tax, String category) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.mrp = mrp;
        this.tax = tax;
        this.category = category;
    }
}
