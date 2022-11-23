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
    public Account getById(String id) {
        return accountRepository.getReferenceById(id);
    }


    @Override
    public List<Account> getByOwner(Customer customer) {
        return accountRepository.getAccountsByOwner(customer);
    }

    @Override
    public List<Account> getAllAccounts() {
//        List<Account> notApproved = accountRepository.getAccountsByApprovedIsFalse();
//        List<Account> approved = accountRepository.getAccountsByApprovedIsTrue();
//        return Stream.concat(notApproved.stream(), approved.stream())
//                .collect(Collectors.toList());
        return accountRepository.getAllByIdIsNotNull();
    }

    @Override
    public List<Account> getPendingAccount() {
        return accountRepository.getAccountsByStatus(Status.Pending);
    }

    @Override
    public List<Account> getDisabledAccount() {
        return accountRepository.getAccountsByStatus(Status.Disabled);
    }

    @Override
    public List<Account> getEnabledAccount() {
        return accountRepository.getAccountsByStatus(Status.Enabled);
    }


    @Override
    public List<Account> getByApprovedBy(Staff staff) {
        return accountRepository.getAccountsByApprovedBy(staff);
    }

    @Override
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account save(AccountValidation accountValidation){
        Account account = new Account(null, accountValidation.getType(), Float.valueOf(0), null, null, null, null, null, null, accountValidation.getLastModified());
        // ToDO
        // Need to use session to identify the customer and perform deep copy
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

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }
}
