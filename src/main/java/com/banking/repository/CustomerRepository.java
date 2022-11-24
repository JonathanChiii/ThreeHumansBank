package com.banking.repository;

import com.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByUsername(String username);
    Customer findCustomerByAadhaar(String Aadhaar);
    Customer findCustomerByPAN(String PAN);
}
