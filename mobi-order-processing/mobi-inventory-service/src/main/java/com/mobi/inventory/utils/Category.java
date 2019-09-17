package com.mobi.inventory.utils;

import lombok.Getter;

@Getter
public enum Category {

    AUTOMOTIVE("Automotive"),
    COMPUTERS("Computers"),
    ELECTRONICS("Electronics"),
    ARTS("Arts"),
    FASHION("Fashion"),
    HEALTH("Health");

    String key;

    Category(String value) {
        key = value;
    }
}


