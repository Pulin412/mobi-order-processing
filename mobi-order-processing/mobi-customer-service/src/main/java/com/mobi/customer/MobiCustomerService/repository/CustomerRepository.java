package com.mobi.customer.MobiCustomerService.repository;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByCustomerName(String name);
}
