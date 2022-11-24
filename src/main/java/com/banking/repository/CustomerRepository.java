package com.banking.repository;

import com.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerByUsername(String username);
    Optional<Customer> findCustomerByAadhaar(String Aadhaar);
    Optional<Customer> findCustomerByPAN(String PAN);
}
