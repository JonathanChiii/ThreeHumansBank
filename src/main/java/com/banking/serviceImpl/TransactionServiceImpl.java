package com.banking.serviceImpl;

import com.banking.model.Transaction;
import com.banking.repository.TransactionRepository;
import com.banking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
