package com.mobi.customer.MobiCustomerService.service;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import com.mobi.customer.MobiCustomerService.exception.RecordNotFoundException;
import com.mobi.customer.MobiCustomerService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
     
    @Autowired
    CustomerRepository repository;
     
    public List<Customer> getAllEmployees()
    {
        List<Customer> employeeList = repository.findAll();
         
        if(employeeList.size() > 0) {
            return employeeList;
        } else {
            return new ArrayList<Customer>();
        }
    }
     
    public Customer getEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<Customer> employee = repository.findById(id);
         
        if(employee.isPresent()) {
            return employee.get();
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
     
    public Customer createOrUpdateEmployee(Customer entity) throws RecordNotFoundException
    {
        Optional<Customer> employee = repository.findById(entity.getId());
         
        if(employee.isPresent())
        {
            Customer newEntity = employee.get();
            newEntity.setEmail(entity.getEmail());
            newEntity.setFirstName(entity.getFirstName());
            newEntity.setLastName(entity.getLastName());
 
            newEntity = repository.save(newEntity);
             
            return newEntity;
        } else {
            entity = repository.save(entity);
             
            return entity;
        }
    }
     
    public void deleteEmployeeById(Long id) throws RecordNotFoundException
    {
        Optional<Customer> employee = repository.findById(id);
         
        if(employee.isPresent())
        {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No employee record exist for given id");
        }
    }
}