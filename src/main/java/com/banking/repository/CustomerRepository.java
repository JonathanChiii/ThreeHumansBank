package com.banking.repository;

import com.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> getAllByIdIsNotNull(); // Haven't found any better way to get all customers
    Customer getCustomerByUsername(String username);
    Customer getCustomerByAadhaar(String Aadhaar);
    Customer getCustomerByPAN(String PAN);
}
