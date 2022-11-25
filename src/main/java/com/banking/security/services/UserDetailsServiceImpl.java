package com.banking.security.services;

import com.banking.model.BankUser;
import com.banking.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// UseDetailsService interface has only one method: to load User by username and return a UserDetails object for authentication and validation
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    BankUserRepository bankUserRepository;

    // This method build a userDetails object for authentication and validation(used in AuthToken Filter), using the static build methods
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BankUser bankUser = bankUserRepository.findBankUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+ username));
        return UserDetailsImpl.build(bankUser);
    }
}
