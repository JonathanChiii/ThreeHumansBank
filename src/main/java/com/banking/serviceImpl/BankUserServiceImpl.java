package com.banking.serviceImpl;

import com.banking.model.BankUser;
import com.banking.repository.BankUserRepository;
import com.banking.service.BankUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankUserServiceImpl implements BankUserService {

    @Autowired
    BankUserRepository bankUserRepository;

    @Override
    public BankUser getById(Long id) {
        return bankUserRepository.getReferenceById(id);
    }

    @Override
    public BankUser getByUsername(String username) {
        return bankUserRepository.getBankUserByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return bankUserRepository.existsByUsername(username);
    }
}
