package com.banking.service;

import com.banking.dto.CustomerValidation;
import com.banking.model.Customer;

public interface CustomerService {

    Customer getById(String id);
    Customer getByUsername(String username);
    Customer getByAadhaar(String Aadhaar);
    Customer getByPAN(String PAN);
    void save(Customer customer);
    void save(CustomerValidation customerValidation);
    Customer update(Customer customer);
    void delete(Customer customer);
}
