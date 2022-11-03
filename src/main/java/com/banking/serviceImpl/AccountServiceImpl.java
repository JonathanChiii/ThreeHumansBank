package com.banking.serviceImpl;

import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Account findById(UUID id) {
        return null;
    }

    @Override
    public List<Account> findByOwner(Customer customer) {
        return null;
    }
}
