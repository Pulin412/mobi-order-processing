package com.mobi.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ResponseDto {
    String message;
    String status;
    List<ProductDto> productDtoList;
}
