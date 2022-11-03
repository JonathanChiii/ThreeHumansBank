package com.banking.service;

import com.banking.dto.CustomerValidation;
import com.banking.dto.UserLogin;
import com.banking.model.Customer;

public interface CustomerService {

    void save(Customer customer);
    void save(CustomerValidation customerValidation);
    Boolean update(Customer customer);
    Customer findById(Long id);
    Customer findByUsername(String username);
    Integer login(UserLogin userLogin);
    // This should return status code


}
