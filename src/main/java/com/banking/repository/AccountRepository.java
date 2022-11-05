package com.banking.repository;

import com.banking.model.Account;
import com.banking.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Account findAccountByNumber(Long number);
    List<Account> findAccountsByOwner(Customer owner);
}
