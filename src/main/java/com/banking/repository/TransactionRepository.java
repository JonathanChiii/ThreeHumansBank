package com.banking.repository;

import com.banking.model.Account;
import com.banking.model.BankUser;
import com.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findTransactionsBySourceAccount(Account account);
    List<Transaction> findTransactionsByDestAccount(Account account);
    List<Transaction> findTransactionsByBankUser(BankUser bankUser);
    Transaction save(Transaction transaction);
}
