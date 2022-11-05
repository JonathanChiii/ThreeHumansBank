package com.banking.controller;

import com.banking.dto.AccountValidation;
import com.banking.dto.CustomerValidation;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.service.AccountService;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public Customer register(@RequestBody @Valid CustomerValidation customerValidation) {
        return customerService.save(customerValidation);
    }

    //@GetMapping("/authenticate")

    @PostMapping("/{id}/account")
    public Account openAccount(@RequestBody @Valid AccountValidation accountValidation){
        return accountService.save(accountValidation);
    }


}
