package com.mobi.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDto {

    private String customerName;
    private String address;
    private String email;
    private Long contact;

}
