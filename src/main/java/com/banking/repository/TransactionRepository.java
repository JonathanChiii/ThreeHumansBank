package com.banking.repository;

import com.banking.model.Account;
import com.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, String> {

    List<Transaction> findTransactionsBySourceAccount(Account account);
    List<Transaction> findTransactionsByDestAccount(Account account);
}
