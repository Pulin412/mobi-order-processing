package com.mobi.customer.MobiCustomerService.service;


import com.mobi.customer.MobiCustomerService.util.CustomerServiceConstants;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import com.mobi.customer.MobiCustomerService.exception.RecordNotFoundException;
import com.mobi.customer.MobiCustomerService.model.CustomerDto;
import com.mobi.customer.MobiCustomerService.model.ResponseDto;
import com.mobi.customer.MobiCustomerService.repository.CustomerRepository;


import com.mobi.customer.MobiCustomerService.util.CustomerServiceUtil;
import lombok.extern.slf4j.Slf4j;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {


  @Autowired CustomerRepository customerRepository;

  @Autowired CustomerServiceUtil customerServiceUtil;

  String responseMsg = "";

  public ResponseDto getAllCustomers() {
      log.info("Reading through Service class");
    List<Customer> customerList = customerRepository.findAll();
    responseMsg = CustomerServiceConstants.CUSTOMER_LIST;
    ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
    if (customerList.isEmpty()) {
      responseMsg = CustomerServiceConstants.NO_RECORDS_FOUND;
      responseDto.setMessage(responseMsg);
    } else {
      log.debug(" CustomerService : Fetching all Customer records from table : {}");
      responseDto.setMessage(responseMsg);
      responseDto.setCustomerDtoList(
          customerServiceUtil.customerListToCustomerDtoList(customerList));
    }
    return responseDto;
  }

  public ResponseDto getCustomerById(Long id) {
    Optional<Customer> optionalCustomer = customerRepository.findById(id);
    ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
    CustomerDto customerDto = null;
    List<CustomerDto> customerDtoList = new ArrayList<>();
    if (optionalCustomer.isPresent()) {
      customerDtoList.add(customerServiceUtil.convertToDto(optionalCustomer.get()));
      responseDto.setMessage(CustomerServiceConstants.CUSTOMER_LIST);
      responseDto.setCustomerDtoList(customerDtoList);
      log.debug("CustomerService : Fetching Customer details with id: {}", id);
    } else {
      responseDto.setMessage(CustomerServiceConstants.NO_RECORDS_FOUND + "with id: " + id);
    }
    return responseDto;
  }

  public ResponseDto createCustomer(CustomerDto customerDto) throws RecordNotFoundException {
    List<CustomerDto> customerDtoList = new ArrayList<>();
    Customer customer;
    customer = customerRepository.save(customerServiceUtil.convertToEntity(customerDto));
    customerDtoList.add(customerServiceUtil.convertToDto(customer));
    log.debug("CustomerService : creating new customer : {}");
    return new ResponseDto(
        "New customer created Successfully", HttpStatus.OK.toString(), customerDtoList);
  }

  public ResponseDto updateCustomer(Long id, CustomerDto customerDto) {
    Optional<Customer> customerOptional = customerRepository.findById(id);
    ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
    List<CustomerDto> customerDtoList = new ArrayList<>();
    if (customerOptional.isPresent()) {
      Customer customer = customerOptional.get();
      customerRepository.save(customerServiceUtil.convertToEntity(customerDto));
      customerDtoList.add(customerServiceUtil.convertToDto(customer));
      responseDto.setMessage(CustomerServiceConstants.CUSTOMER_RECORDS_UPDATED);
      responseDto.setCustomerDtoList(customerDtoList);
    } else {
      responseDto.setMessage(CustomerServiceConstants.NO_RECORDS_FOUND);
    }
    return responseDto;
  }

  public ResponseDto deleteCustomerById(Long id) throws RecordNotFoundException {
    Optional<Customer> customerOptional = customerRepository.findById(id);
    List<CustomerDto> customerDtoList = new ArrayList<>();
    responseMsg = "Customer Deleted Successfully...!!";
    ResponseDto responseDto = new ResponseDto("", HttpStatus.OK.toString(), null);
    if (customerOptional.isPresent()) {
      customerRepository.deleteById(id);
      customerDtoList.add(customerServiceUtil.convertToDto(customerOptional.get()));
      responseDto.setMessage(responseMsg);
      responseDto.setCustomerDtoList(customerDtoList);
    } else {
      responseDto.setMessage(CustomerServiceConstants.NO_RECORDS_FOUND + "with id: " + id);
    }
    return responseDto;
  }
}
