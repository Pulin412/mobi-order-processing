package com.mobi.customer.MobiCustomerService.entity;

import lombok.Data;

import lombok.extern.slf4j.Slf4j;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
@Data
@AllArgsConstructor

@NoArgsConstructor
@Slf4j
@Entity
@Table(name = "CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_address", nullable = false, length = 200)
    private String address;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "contact_details", nullable = false, length = 15)
    private Long contact;


}