package com.banking.service;

import com.banking.dto.AccountValidation;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.Staff;

import java.util.List;

public interface AccountService {

    Account getById(Long id);
    List<Account> getByOwner(Customer owner);
    List<Account> getAllAccounts();
    List<Account> getNotApprovedAccount();
    List<Account> getAccountsApprovedBy(Staff staff);
    Account save(Account account);
    Account save(AccountValidation accountValidation);
    Account update(Account account);
    void delete(Account account);
}
