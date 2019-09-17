package com.mobi.customer.MobiCustomerService.web;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import com.mobi.customer.MobiCustomerService.exception.RecordNotFoundException;
import com.mobi.customer.MobiCustomerService.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController
{
    @Autowired
    CustomerService service;
 
    @GetMapping
    public ResponseEntity<List<Customer>> getAllEmployees() {
        List<Customer> list = service.getAllEmployees();
 
        return new ResponseEntity<List<Customer>>(list, new HttpHeaders(), HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        Customer entity = service.getEmployeeById(id);
 
        return new ResponseEntity<Customer>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
    @PostMapping
    public ResponseEntity<Customer> createOrUpdateEmployee(Customer employee)
                                                    throws RecordNotFoundException {
        Customer updated = service.createOrUpdateEmployee(employee);
        return new ResponseEntity<Customer>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteEmployeeById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteEmployeeById(id);
        return HttpStatus.FORBIDDEN;
    }
 
}