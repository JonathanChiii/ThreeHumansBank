package com.banking.repository;

import com.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByUsername(String username);
    Customer findCustomerByAadhaar(String Aadhaar);
    Customer findCustomerByPAN(String PAN);
}
