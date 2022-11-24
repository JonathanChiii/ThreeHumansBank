package com.banking.service;

import com.banking.model.BankUser;

import java.util.List;
import java.util.Optional;

public interface BankUserService {
    BankUser findById(Long id);
    BankUser findByUsername(String username);
    Boolean existsByUsername(String username);
}
