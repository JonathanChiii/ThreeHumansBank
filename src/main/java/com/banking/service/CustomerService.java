package com.banking.service;

import com.banking.dto.CustomerValidation;
import com.banking.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer findByUsername(String username);
    Customer findByAadhaar(String Aadhaar);
    Customer findByPAN(String PAN);
    Customer save(Customer customer);
    Customer update(Customer customer);
}
