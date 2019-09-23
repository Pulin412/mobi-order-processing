package com.mobi.customer.MobiCustomerService.Controller;

import com.mobi.customer.MobiCustomerService.exception.RecordNotFoundException;
import com.mobi.customer.MobiCustomerService.model.CustomerDto;
import com.mobi.customer.MobiCustomerService.model.ResponseDto;
import com.mobi.customer.MobiCustomerService.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/getAllCustomer")
    public ResponseDto getAllCustomers() {
        log.debug("Fetching all Customer records from table : {}");
        return customerService.getAllCustomers();
    }

    @GetMapping("/getCustomer/{id}")
    public ResponseDto getCustomerById(@PathVariable Long id) {
        log.debug("Fetching Customer details with id: {}", id);
        return customerService.getCustomerById(id);
    }

    @PostMapping("/")
    public ResponseDto createCustomer(@RequestBody CustomerDto customer)
            throws RecordNotFoundException {
        log.debug("creating new customer : {}", customer);
        return customerService.createCustomer(customer);
    }

    @PutMapping("/update/{id}")
    public ResponseDto updateCustomer(@RequestBody CustomerDto customer, @PathVariable Long id)
            {
        log.debug("updating customer with id : {}", id);
        return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDto deleteCustomerById(@PathVariable("id") Long id) throws RecordNotFoundException {
        log.debug("Deleting Customer records with id: {}", id);
        return customerService.deleteCustomerById(id);

    }

}