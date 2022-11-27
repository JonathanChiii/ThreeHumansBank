package com.banking.repository;

import com.banking.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankUserRepository extends JpaRepository<BankUser, Long> {
    Optional<BankUser> findBankUserByUsername(String username);
    Boolean existsByUsername(String username);
}
