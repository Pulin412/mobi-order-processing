package com.mobi.customer.MobiCustomerService.repository;

import com.mobi.customer.MobiCustomerService.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

}
