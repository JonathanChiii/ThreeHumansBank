package com.banking.serviceImpl;

import com.banking.model.Account;
import com.banking.model.BankUser;
import com.banking.model.Transaction;
import com.banking.repository.TransactionRepository;
import com.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction findById(String id) {
        return transactionRepository.getReferenceById(id);
    }

    @Override
    public List<Transaction> findBySourceAccount(Account account) {
        return transactionRepository.findTransactionsBySourceAccount(account);
    }

    @Override
    public List<Transaction> findByDestAccount(Account account) {
        return transactionRepository.findTransactionsByDestAccount(account);
    }

    @Override
    public List<Transaction> findByBankUser(BankUser bankUser) {
        return transactionRepository.findTransactionsByBankUser(bankUser);
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
