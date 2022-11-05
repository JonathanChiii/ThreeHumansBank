package com.banking.serviceImpl;

import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(UUID id) {
        return accountRepository.getReferenceById(id);
    }

    @Override
    public Account findByNumber(Long number) {
        return accountRepository.findAccountByNumber(number);
    }

    @Override
    public List<Account> findByOwner(Customer customer) {
        return accountRepository.findAccountsByOwner(customer);
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        Account a = accountRepository.getReferenceById(account.getId());
        if(a != null){
            accountRepository.save(account);
            return account;
        }else return null;
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
