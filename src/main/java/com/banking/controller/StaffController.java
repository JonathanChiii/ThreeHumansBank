package com.banking.controller;

import com.banking.dto.Transfer;
import com.banking.model.Account;
import com.banking.model.Customer;
import com.banking.model.ModelUtility.ERole;
import com.banking.model.Role;
import com.banking.model.ModelUtility.Status;
import com.banking.model.Staff;
import com.banking.model.Beneficiary;
import com.banking.service.StaffService;
import com.banking.service.TransactionService;
import com.banking.service.AccountService;
import com.banking.service.BeneficiaryService;
import com.banking.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    CustomerService customerService;
    
    @Autowired
    TransactionService transactionService;
    
    @Autowired
    BeneficiaryService beneficiaryService;

    @GetMapping("test1")
    public Staff test1(){
        Staff staff = new Staff(null, "Staff1", "Chris Evans", "0000", Set.of(new Role(ERole.Customer), new Role(ERole.Staff)),null, Status.Enabled, null, null);
        return staffService.save(staff);
    }
    
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public int authenticate(@RequestBody String username, String password){
        //ToDo
    	return 1;
    }

    @RequestMapping(value = "/account/:accountNo", method = RequestMethod.GET)
    public Account getAccountStatement(@PathVariable("accountNo") Long accountNo){
    	Account info = accountService.getById(accountNo);
    	return accountService.getAccountStatement(info);
    }
    
    @RequestMapping(value = "/beneficiary", method = RequestMethod.GET)
    public List<Beneficiary> getBeneficiary(){
        return beneficiaryService.getAllNotApproved();
    }
    
    @RequestMapping(value = "/beneficiary", method = RequestMethod.PUT)
    public Beneficiary approveBeneficiary(@RequestBody Beneficiary beneficiary){
        //ToDo
    	return beneficiaryService.update(beneficiary);
    }
    
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public List<Customer> getAllCustomer() {
    	return customerService.getAllCustomers();
    }
    
    @RequestMapping(value = "/customer", method = RequestMethod.PUT)
    public void changeCustomerStatus(@RequestBody Customer customer) {
    	Customer toChange = customerService.getById(customer.getId());
    	toChange.setStatus(customer.getStatus());
    	customerService.save(toChange);
    }
    
    @RequestMapping(value = "/customer/:customerID", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("customerID") Long Id){
    	return customerService.getById(Id);
    }
    
    @RequestMapping(value = "/transfer", method = RequestMethod.PUT)
    public void transfer(@RequestBody Transfer transfer) {
    	
    	transactionService.doTransfer(transfer);
    }
}
