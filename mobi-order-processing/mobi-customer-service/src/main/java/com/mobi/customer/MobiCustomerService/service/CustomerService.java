package com.mobi.customer.MobiCustomerService.service;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import com.mobi.customer.MobiCustomerService.exception.RecordNotFoundException;
import com.mobi.customer.MobiCustomerService.model.CustomerDto;
import com.mobi.customer.MobiCustomerService.model.ResponseDto;
import com.mobi.customer.MobiCustomerService.repository.CustomerRepository;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ResponseDto getAllCustomers() {
        List<Customer> customerList = customerRepository.findAll();

        if (customerList.size() > 0) {

            log.debug(" CustomerService : Fetching all Customer records from table : {}");
            return new ResponseDto("List of Customer", HttpStatus.OK.toString(), customerListToCustomerDtoList(customerList));
        } else {
            return new ResponseDto("Records Not Found", HttpStatus.OK.toString(), null);
        }
    }

    public ResponseDto getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);

        CustomerDto customerDto = null;
        List<CustomerDto> customerDtoList = new ArrayList<>();
        ResponseDto responseDto;
        //TODO map customer entiry to DTO
        if (customer.isPresent()) {
            customerDto = new CustomerDto("", "", "", 0L);
            customerDto.setAddress(customer.get().getAddress());
            customerDto.setCustomerName(customer.get().getCustomerName());
            customerDto.setContact(customer.get().getContact());
            customerDto.setEmail(customer.get().getEmail());
            customerDtoList.add(customerDto);
            log.debug("CustomerService : Fetching Customer details with id: {}", id);
            return new ResponseDto("Records Found", HttpStatus.OK.toString(), customerDtoList);
        } else {
            return new ResponseDto("Records Not Found", HttpStatus.OK.toString(), null);
        }
    }

    public ResponseDto createCustomer(CustomerDto customerDto) throws RecordNotFoundException {

        List<CustomerDto> customerDtoList = new ArrayList<>();
        Customer customer = new Customer();
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setAddress(customerDto.getAddress());
        customer.setEmail(customerDto.getEmail());
        customer.setContact(customerDto.getContact());

        customerRepository.save(customer);
        customerDtoList.add(convertToDto(customer));
        log.debug("CustomerService : creating new customer : {}");
        return new ResponseDto("New customer created Successfully", HttpStatus.OK.toString(), customerDtoList);
    }

    public ResponseDto updateCustomer(Long id, CustomerDto customerDto) {
        Optional<Customer> updateCustomerRecord = customerRepository.findById(id);

        List<CustomerDto> customerDtoList = new ArrayList<>();
        if (updateCustomerRecord.isPresent()) {
            Customer customer = updateCustomerRecord.get();
            customer.setCustomerName(customerDto.getCustomerName());
            customer.setAddress(customerDto.getAddress());
            customer.setEmail(customerDto.getEmail());
            customer.setContact(customerDto.getContact());
            customerRepository.save(customer);
            customerDtoList.add(convertToDto(customer));
            return new ResponseDto("customer record updated successfully", HttpStatus.OK.toString(), customerDtoList);
        } else {
            return new ResponseDto("No customer record found with " + id + "in database", HttpStatus.OK.toString(), null);
        }
    }

    public ResponseDto deleteCustomerById(Long id) throws RecordNotFoundException {
        Optional<Customer> customer = customerRepository.findById(id);
        List<CustomerDto> customerDtoList = new ArrayList<>();

        if (customer.isPresent()) {
            customerRepository.deleteById(id);
            customerDtoList.add(convertToDto(customer.get()));
            return new ResponseDto("Customer Deleted Successfully", HttpStatus.OK.toString(), customerDtoList);
        } else {
            throw new RecordNotFoundException("No customer record exist for given " + id);
        }
    }

    private List<CustomerDto> customerListToCustomerDtoList(List<Customer> customerList) {
        List<CustomerDto> customerDtoList = new ArrayList<>();
        for (Customer customer : customerList) {
            customerDtoList.add(convertToDto(customer));
        }
        return customerDtoList;
    }

    private List<Customer> customerDtoListToCustomerList(List<CustomerDto> customerDtoList) {
        List<Customer> customerList = new ArrayList<>();
        for (CustomerDto customerDto : customerDtoList) {
            customerList.add(convertToEntity(customerDto));
        }
        return customerList;
    }

    private CustomerDto convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    private Customer convertToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }
}