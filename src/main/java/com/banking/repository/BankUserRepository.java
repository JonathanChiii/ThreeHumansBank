package com.banking.repository;

import com.banking.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    BankUser getBankUserByUsername(String username);
    Boolean existsByUsername(String username);
}
