package com.banking.service;

import com.banking.dto.CustomerValidation;
import com.banking.dto.UserLogin;
import com.banking.model.Customer;

public interface CustomerService {

    Customer findById(Long id);
    Customer findByUsername(String username);
    Customer findByAadhaar(String Aadhaar);
    Customer findByPAN(String PAN);
    void save(Customer customer);
    void save(CustomerValidation customerValidation);
    Customer update(Customer customer);
    void delete(Customer customer);
}
