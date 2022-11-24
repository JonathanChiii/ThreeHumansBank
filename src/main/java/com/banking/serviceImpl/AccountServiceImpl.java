package com.banking.serviceImpl;

import com.banking.dto.AccountValidation;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import com.banking.repository.AccountRepository;
import com.banking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findById(String id) {
        return accountRepository.getReferenceById(id);
    }

    @Override
    public List<Account> findByOwner(Customer owner) {
        return accountRepository.findAccountsByOwner(owner);
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> findPendingAccounts() {
        return accountRepository.findAccountsByStatus(Status.Pending);
    }

    @Override
    public List<Account> findDisabledAccounts() {
        return accountRepository.findAccountsByStatus(Status.Disabled);
    }

    @Override
    public List<Account> findEnabledAccounts() {
        return accountRepository.findAccountsByStatus(Status.Enabled);
    }

    @Override
    public List<Account> findByApprovedBy(Staff staff) {
        return accountRepository.findAccountsByApprovedBy(staff);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account update(Account account) {
        Account a = accountRepository.getReferenceById(account.getId());
        if(a != null){
            return accountRepository.save(account);
        }
        return null;
    }
}
