package com.banking.controller;

import com.banking.dto.ChangeBankUserStatus;
import com.banking.dto.TransactionValidation;
import com.banking.model.*;
import com.banking.model.ModelUtility.ERole;
import com.banking.model.ModelUtility.Status;
import com.banking.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    BankUserService bankUserService;
    @Autowired
    StaffService staffService;
    @Autowired
    CustomerService customerService;
    @Autowired
    BeneficiaryService beneficiaryService;
    @Autowired
    AccountService accountService;
    @Autowired
    TransactionService transactionService;

    @GetMapping("test1")
    public Staff test1(){
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(ERole.Customer));
        roleSet.add(new Role(ERole.Staff));
        Staff staff = new Staff(null, "CaptainAmerica", "Chris Evans", "0000", Status.Enabled, null, roleSet,null, null, null);
        return staffService.save(staff);
    }

    @RequestMapping(value = "/register", method = RequestMethod.PUT)
    public Staff register(@RequestBody Staff staff){
        //ToDo
        //Need to check if current staff username exists
        return staffService.save(staff);
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public int authenticate(@RequestBody String username, String password){
        //ToDo
        return 1;
    }

    @RequestMapping(value = "/account/:accountNo", method = RequestMethod.GET)
    public Account getAccountStatement(@PathVariable("accountNo") String accountNo){
        Account account = accountService.findById(accountNo);
        return account;
    }

    @RequestMapping(value = "/beneficiary", method = RequestMethod.GET)
    public List<Beneficiary> getBeneficiary(){
        return beneficiaryService.findByNotApproved();
    }

    @RequestMapping(value = "/beneficiary", method = RequestMethod.PUT)
    public Beneficiary approveBeneficiary(@RequestBody Beneficiary beneficiary){
        // ToDo
        beneficiary.setIsApproved(true);
        beneficiary.setApprovedBy(null);
        //ToDo: need to identify the staff using token
        return beneficiaryService.update(beneficiary);
    }

    @RequestMapping(value = "/accounts/approve", method = RequestMethod.GET)
    public List<Account> getPendingAccounts() {
        return accountService.findPendingAccounts();
    }

    @RequestMapping(value = "/accounts/approve", method = RequestMethod.PUT)
    public HttpStatus approvePendingAccounts(@RequestBody Account account) {
        return HttpStatus.OK;
        // ToDo

    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomer() {
        return customerService.findAllCustomers();
    }

    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public ResponseEntity<String> changeCustomerStatus(@RequestBody @Valid ChangeBankUserStatus changeBankUserStatus) {
        BankUser bankUser = bankUserService.findById(changeBankUserStatus.getId());
        if(! bankUser.getRoles().contains(new Role(ERole.Staff))){
            Customer targetCustomer = customerService.findById(changeBankUserStatus.getId());
            targetCustomer.setStatus(changeBankUserStatus.getStatus());
            customerService.save(targetCustomer);
            return ResponseEntity.status(HttpStatus.OK).body("Customer status changed successfully.\n");
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot change the status of this user.\n");
    }

    @RequestMapping(value = "/customer/:customerID", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("customerID") Long Id){
        return customerService.findById(Id);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public ResponseEntity<String> transfer(@RequestBody @Valid TransactionValidation transactionValidation) {
        Account srcAccount = accountService.findById(transactionValidation.getSourceAccountNumber());
        if(srcAccount == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Source account does not exist.\n");
        }
        Account destAccount = accountService.findById(transactionValidation.getDestAccountNumber());
        if(destAccount == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Destination account does not exist.\n");
        }
        if(srcAccount.getBalance() < transactionValidation.getAmount()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The source account does not have enough balance.\n");
        }
        Transaction transaction = new Transaction(null, null, srcAccount, destAccount, transactionValidation.getAmount(), transactionValidation.getReason(), transactionValidation.getTimestamp());
        // ToDo: need to identify the user with JWT token
        transactionService.save(transaction);
        return ResponseEntity.status(HttpStatus.OK).body("Transaction Completed.\n");
    }

}
