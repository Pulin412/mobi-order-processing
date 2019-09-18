package com.mobi.inventory.dto;

import lombok.Data;

@Data
public class ProductDto {

    Long productId;
    String name;
    String description;
    Double basePrice;
    Double mrp;
    Double tax;
    String category;
    Integer quantity;
}
