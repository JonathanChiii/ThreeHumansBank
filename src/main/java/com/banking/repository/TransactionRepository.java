package com.banking.repository;

import com.banking.model.Account;
import com.banking.model.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<AccountTransaction, String> {

    List<AccountTransaction> findAccountTransactionBySourceAccount(Account account);
    List<AccountTransaction> findAccountTransactionByDestAccount(Account account);
}
