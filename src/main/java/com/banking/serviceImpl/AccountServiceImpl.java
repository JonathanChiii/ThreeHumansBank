package com.banking.serviceImpl;

import com.banking.dto.AccountValidation;
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
    public Account getById(Long id) {
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
    public Account save(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public Account save(AccountValidation accountValidation){
        Account account = new Account(null, accountValidation.getType(), Float.valueOf(0), null, false, null, null, null, null, null, accountValidation.getLastModified());
        // ToDO
        // Need to use session to identify the customer and perform deep copy
        return accountRepository.save(account);

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


	@Override
	public Account getAccountStatement(Account account) {
		Account ret = new Account();
		ret.setId(account.getId());
    	ret.setOwner(account.getOwner());
    	ret.setBalance(account.getBalance());
    	ret.setTransferIn(account.getTransferIn());
    	ret.setTransferOut(account.getTransferOut());
		return ret;
	}
}
