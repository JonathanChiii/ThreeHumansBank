//package com.banking.security.services;
//
//import com.banking.model.BankUser;
//import com.banking.repository.BankUserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    BankUserRepository bankUserRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        BankUser bankUser = bankUserRepository.findBankUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: "+ username));
//        return null;
//    }
//}
