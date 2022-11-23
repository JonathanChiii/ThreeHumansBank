package com.banking.service;

import com.banking.model.BankUser;

public interface BankUserService {
    BankUser getById(Long id);
    BankUser getByUsername(String username);
    Boolean existsByUsername(String username);
}
