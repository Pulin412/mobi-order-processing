package com.mobi.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * The type Product.
 */
@Entity
@Data
@Table(name = "PRODUCT")
@NoArgsConstructor
public class Product {

    /**
     * The Product id.
     */
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long productId;

    /**
     * The Name.
     */
    @Column(name = "name")
    String name;

    /**
     * The Description.
     */
    @Column(name = "description")
    String description;

    /**
     * The Base price.
     */
    @Column(name = "base_price")
    Double basePrice;

    /**
     * The Mrp.
     */
    @Column(name = "mrp")
    Double mrp;

    /**
     * The Tax.
     */
    @Column(name = "tax")
    Double tax;

    /**
     * The Category.
     */
    @Column(name = "category")
    String category;

    /**
     * The Quantity.
     */
    @Column(name = "quantity")
    Integer quantity = 0;

    /**
     * Instantiates a new Product.
     *
     * @param name        the name
     * @param description the description
     * @param basePrice   the base price
     * @param mrp         the mrp
     * @param tax         the tax
     * @param category    the category
     */
    public Product(String name, String description, Double basePrice, Double mrp, Double tax, String category) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.mrp = mrp;
        this.tax = tax;
        this.category = category;
    }
}
