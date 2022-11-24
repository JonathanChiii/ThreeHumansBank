package com.banking.serviceImpl;

import com.banking.model.BankUser;
import com.banking.repository.BankUserRepository;
import com.banking.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankUserServiceImpl implements BankUserService {

    @Autowired
    BankUserRepository bankUserRepository;

    @Override
    public BankUser findById(Long id) {
        return bankUserRepository.getReferenceById(id);
    }

    @Override
    public BankUser findByUsername(String username) {
        return bankUserRepository.findBankUserByUsername(username).orElse(null);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return bankUserRepository.existsByUsername(username);
    }
}
