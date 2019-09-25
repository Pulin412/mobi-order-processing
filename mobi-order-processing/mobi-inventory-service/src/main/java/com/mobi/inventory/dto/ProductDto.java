package com.mobi.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Product dto.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    /**
     * The Product id.
     */
    Long productId;
    /**
     * The Name.
     */
    String name;
    /**
     * The Description.
     */
    String description;
    /**
     * The Base price.
     */
    Double basePrice;
    /**
     * The Mrp.
     */
    Double mrp;
    /**
     * The Tax.
     */
    Double tax;
    /**
     * The Category.
     */
    String category;
    /**
     * The Quantity.
     */
    Integer quantity;
}
