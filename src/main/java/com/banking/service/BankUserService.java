package com.banking.service;

import com.banking.model.BankUser;

public interface BankUserService {
    BankUser findById(Long id);
    BankUser findByUsername(String username);
    BankUser findByFullName(String fullName);
    Boolean existsByUsername(String username);
}
