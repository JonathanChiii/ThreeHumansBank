package com.banking.service;

import com.banking.dto.AccountValidation;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.Staff;

import java.util.List;

public interface AccountService {

    Account findById(String id);
    List<Account> findByOwner(Customer owner);
    List<Account> findAllAccounts();
    List<Account> findPendingAccounts();
    List<Account> findDisabledAccounts();
    List<Account> findEnabledAccounts();
    List<Account> findByApprovedBy(Staff staff);
    Account save(Account account);
    //Account save(AccountValidation accountValidation);
    Account update(Account account);
    //void delete(Account account);
}
