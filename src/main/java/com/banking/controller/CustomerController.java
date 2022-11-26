package com.banking.controller;

import com.banking.dto.AccountValidation;
import com.banking.dto.ChangeStatus;
import com.banking.dto.payload.request.SignupRequest;
import com.banking.dto.payload.response.MessageResponse;
import com.banking.model.*;
import com.banking.model.ModelUtility.ERole;
import com.banking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    StaffService staffService;
    @Autowired
    AccountService accountService;
    @Autowired
    BankUserService bankUserService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleService roleService;
    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody SignupRequest signupRequest){
        if(bankUserService.existsByUsername(signupRequest.getUsername())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: this username is already taken."));
        }
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(ERole.Customer));
        Customer customer = new Customer(signupRequest.getUsername(), signupRequest.getFullName(), passwordEncoder.encode(signupRequest.getPassword()), roles);
        customerService.save(customer);
        customer.setPassword("****");
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }


    // https://www.baeldung.com/get-user-in-spring-security
    // This is life-saving tutorial
    @RequestMapping(value = "/account", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Customer')")
    public ResponseEntity<?> createAccount( @RequestBody @Valid AccountValidation accountValidation, HttpServletRequest request){
        String username = request.getUserPrincipal().getName();
        Customer customer = customerService.findByUsername(username);
        if(customer != null){
            Account account = new Account(accountValidation.getType(), accountValidation.getBalance(), customer, accountValidation.getDateCreated());
            customer.getAccounts().add(account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Cannot find customer information. "));
    }

    // test: input a non-existing customerID, or an id with special chars
    @RequestMapping(value = "/:customerID/account/:accountID", method = RequestMethod.POST)
    @PreAuthorize("hasRole('Staff')")
    public ResponseEntity<?> approveAccount (@PathVariable("customerID") String customerID, @PathVariable("accountID") String accountID, @RequestBody @Valid ChangeStatus changeStatus, HttpServletRequest request){
        try{
            Long.valueOf(customerID);
        }
        catch(NumberFormatException e){
            return ResponseEntity.badRequest().body(new MessageResponse("Customer ID is in illegal format. "));
        }
        Customer customer = customerService.findById(Long.valueOf(customerID));
        if(customer != null){
            Account account = accountService.findByOwnerAndId(customer, accountID);
            if(account != null){
                account.setStatus(changeStatus.getStatus());
                String username = request.getUserPrincipal().getName();
                Staff staff = staffService.findByUsername(username);
                account.setApprovedBy(staff);
                accountService.save(account);
                return new ResponseEntity<>(account, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().body(new MessageResponse("Cannot find account ID: " + accountID + " under customer ID: " + customerID + ". "));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Cannot find customer with ID: " + customerID + ". "));
    }

    @GetMapping(path = "/:customerID/account", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('Staff')")
    public ResponseEntity<?> getCustomerAccounts(@PathVariable("customerID") String customerID){
        try{
            Long.valueOf(customerID);
        }
        catch(NumberFormatException e){
            return ResponseEntity.badRequest().body(new MessageResponse("Customer ID is in illegal format. "));
        }
        Customer customer = customerService.findById(Long.valueOf(customerID));
        if(customer != null){
            List<Account> accounts = accountService.findByOwner(customer);
            return new ResponseEntity<>(accounts, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Cannot find customer with id: " + customerID + ". "));
    }

    @GetMapping(path = "/:customerID", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('Staff')")
    public ResponseEntity<?> getCustomer(@PathVariable("customerID") String customerID){
        try{
            Long.valueOf(customerID);
        }
        catch(NumberFormatException e){
            return ResponseEntity.badRequest().body(new MessageResponse("Customer ID is in illegal format. "));
        }
        Customer customer = customerService.findById(Long.valueOf(customerID));
        if(customer != null){
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Cannot find customer with ID: " + customerID));
    }

    @GetMapping(path = "/getall", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('Staff')")
    public ResponseEntity<?> getAllCustomers(){
        List<Customer> customers = customerService.findAll();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // ToDO

}
