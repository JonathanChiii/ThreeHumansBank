package com.banking.service;

import com.banking.dto.CustomerValidation;
import com.banking.model.Customer;

public interface CustomerService {

    Customer getById(String id);
    Customer getByUsername(String username);
    Customer getByAadhaar(String Aadhaar);
    Customer getByPAN(String PAN);
    Customer save(Customer customer);
    Customer save(CustomerValidation customerValidation);
    Customer update(Customer customer);
    void delete(Customer customer);
}
