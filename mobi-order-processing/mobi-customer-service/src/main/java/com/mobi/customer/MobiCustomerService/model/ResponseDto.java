package com.mobi.customer.MobiCustomerService.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class ResponseDto {

    public String message;
    public String status;
    List<CustomerDto> customerDtoList;


}
