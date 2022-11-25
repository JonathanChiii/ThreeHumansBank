package com.banking.controller;

import com.banking.dto.payload.request.SignupRequest;
import com.banking.dto.payload.response.MessageResponse;
import com.banking.model.*;
import com.banking.model.ModelUtility.AccountType;
import com.banking.model.ModelUtility.ERole;
import com.banking.model.ModelUtility.Question;
import com.banking.model.ModelUtility.Status;
import com.banking.service.AccountService;
import com.banking.service.BankUserService;
import com.banking.service.CustomerService;
import com.banking.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    BankUserService bankUserService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest){
        if(bankUserService.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: this username is already taken."));
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(ERole.Customer));
        Customer customer = (Customer) new BankUser(signupRequest.getUsername(), signupRequest.getFullName(), passwordEncoder.encode(signupRequest.getPassword()), roles);
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @GetMapping("/test1")
    public Customer test1() {
        // Creating customer
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(ERole.Customer));
        Customer customer1 = new Customer(null, "JonathanNoMoney", "Jonathan Chi", "0000", Status.Pending, null, roleSet, null, null, null, "Aadhaar1", null, "PAN1", null);

        // Creating a set of accounts
        Account account1 = new Account(null, AccountType.CA, Float.valueOf("99.99"), null, customer1, null, null, null, null, null);
        Account account2 = new Account(null, AccountType.SB, Float.valueOf("100"), null, customer1, null, null, null, null, null);
        Set<Account> accounts = Set.of(account1, account2);

        // Creating a set of SecurityQuestions
        SecurityQuestion securityQuestion1 = new SecurityQuestion(null, Question.Q3, "Coldplay", customer1);
        SecurityQuestion securityQuestion2 = new SecurityQuestion(null, Question.Q2, "Wang", customer1);
        Set<SecurityQuestion> securityQuestions = Set.of(securityQuestion1, securityQuestion2);

        // Creating transactions
        Transaction transaction1 = new Transaction(null, account1.getOwner(), account1, account2, Float.valueOf("10"), "no reason", null);

        // Set referencing attributes
        customer1.setAccounts(accounts);
        account1.setTransferOut(Set.of(transaction1));
        account2.setTransferIn(Set.of(transaction1));
        customer1.setSecurityQuestions(securityQuestions);

        return customerService.save(customer1);
    }

    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('Staff')")
    public ResponseEntity<?> getAllCustomer(){
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}
