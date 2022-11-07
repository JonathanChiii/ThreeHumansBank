package com.banking.service;

import com.banking.dto.CustomerValidation;
import com.banking.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Customer getById(Long id);
    Customer getByUsername(String username);
    Customer getByAadhaar(String Aadhaar);
    Customer getByPAN(String PAN);
    Customer save(Customer customer);
    Customer save(CustomerValidation customerValidation);
    Customer update(Customer customer);
    void delete(Customer customer);
}
