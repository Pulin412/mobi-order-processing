package com.mobi.customer.MobiCustomerService.util;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import com.mobi.customer.MobiCustomerService.model.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceUtil {


  private ModelMapper modelMapper;

  @Autowired
  public CustomerServiceUtil(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }

  public List<CustomerDto> customerListToCustomerDtoList(List<Customer> customerList) {

    List<CustomerDto> customerDtoList = new ArrayList<>();
    for (Customer customer : customerList) {
      customerDtoList.add(convertToDto(customer));
    }
    return customerDtoList;
  }


  public List<Customer> customerDtoListToCustomerList(List<CustomerDto> customerDtoList) {
 
    List<Customer> customerList = new ArrayList<>();
    for (CustomerDto customerDto : customerDtoList) {
      customerList.add(convertToEntity(customerDto));
    }
    return customerList;
  }


  public CustomerDto convertToDto(Customer customer) {
    return modelMapper.map(customer, CustomerDto.class);
  }

  public Customer convertToEntity(CustomerDto customerDto) {
    return modelMapper.map(customerDto, Customer.class);
  }
}
