package com.banking.service;

import com.banking.model.Account;
import com.banking.model.BankUser;
import com.banking.model.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction findById(String id);
    List<Transaction> findBySourceAccount(Account account);
    List<Transaction> findByDestAccount(Account account);
    List<Transaction> findByBankUser(BankUser bankUser);
    Transaction save(Transaction transaction);
}
