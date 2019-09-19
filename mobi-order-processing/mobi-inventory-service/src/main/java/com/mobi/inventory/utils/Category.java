package com.mobi.inventory.utils;

import lombok.Getter;

/**
 * The enum Category.
 */
@Getter
public enum Category {

    /**
     * Automotive category.
     */
    AUTOMOTIVE("Automotive"),
    /**
     * Computers category.
     */
    COMPUTERS("Computers"),
    /**
     * Electronics category.
     */
    ELECTRONICS("Electronics"),
    /**
     * Arts category.
     */
    ARTS("Arts"),
    /**
     * Fashion category.
     */
    FASHION("Fashion"),
    /**
     * Health category.
     */
    HEALTH("Health");

    /**
     * The Key.
     */
    String key;

    Category(String value) {
        key = value;
    }
}


