package com.banking.serviceImpl;

import com.banking.model.Account;
import com.banking.model.Customer;
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
    public Account getById(String id) {
        return accountRepository.getReferenceById(id);
    }


    @Override
    public List<Account> getByOwner(Customer customer) {
        return accountRepository.getAccountsByOwner(customer);
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> notApproved = accountRepository.getAccountsByApprovedIsFalse();
        List<Account> approved = accountRepository.getAccountsByApprovedIsTrue();
        return Stream.concat(notApproved.stream(), approved.stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<Account> getNotApprovedAccount() {
        return accountRepository.getAccountsByApprovedIsFalse();
    }

    @Override
    public List<Account> getAccountsApprovedBy(Staff staff) {
        return accountRepository.getAccountsByApprovedBy(staff);
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
