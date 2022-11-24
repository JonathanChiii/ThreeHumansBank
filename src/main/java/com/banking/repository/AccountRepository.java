package com.banking.repository;

import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findAccountsByOwner(Customer customer);
    List<Account> findAccountsByStatus(Status status);
    List<Account> findAccountsByApprovedBy(Staff staff);
}
