package com.banking.service;

import com.banking.model.Account;
import com.banking.model.Customer;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account findById(UUID id);
    Account findByNumber(Long number);
    List<Account> findByOwner(Customer owner);
    void save(Account account);
    Account update(Account account);
    void delete(Account account);
}
