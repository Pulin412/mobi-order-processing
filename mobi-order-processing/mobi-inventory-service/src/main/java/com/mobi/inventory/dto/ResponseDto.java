package com.mobi.inventory.dto;

import com.mobi.demo.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * The type Response dto.
 */
@AllArgsConstructor
@Data
public class ResponseDto {
    /**
     * The Message.
     */
    String message;
    /**
     * The Status.
     */
    String status;
    /**
     * The Product dto list.
     */
    List<ProductDto> productDtoList;
}
