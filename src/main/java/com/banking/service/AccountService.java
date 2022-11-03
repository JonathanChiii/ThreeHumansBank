package com.banking.service;

import com.banking.model.Account;
import com.banking.model.Customer;

import java.util.List;
import java.util.UUID;

public interface AccountService {

    Account findById(UUID id);
    List<Account> findByOwner(Customer customer);
}
