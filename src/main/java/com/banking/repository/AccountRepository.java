package com.banking.repository;

import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findAccountsByOwner(Customer owner);
    List<Account> findAccountsByStatus(Status status);
    List<Account> findAccountsByApprovedBy(Staff staff);
}
