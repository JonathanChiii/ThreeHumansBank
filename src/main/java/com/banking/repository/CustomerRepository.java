package com.banking.repository;

import com.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer getCustomerByUsername(String username);
    Customer getCustomerByAadhaar(String Aadhaar);
    Customer getCustomerByPAN(String PAN);
}
