package com.banking.serviceImpl;

import com.banking.dto.CustomerValidation;
import com.banking.model.Customer;
import com.banking.repository.CustomerRepository;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer getById(String id) {
        return customerRepository.getReferenceById(id);
    }

    @Override
    public Customer getByUsername(String username) {
        return customerRepository.getCustomerByUsername(username);
    }

    @Override
    public Customer getByAadhaar(String Aadhaar) {
        return customerRepository.getCustomerByAadhaar(Aadhaar);
    }

    @Override
    public Customer getByPAN(String PAN) {
        return customerRepository.getCustomerByPAN(PAN);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer save(CustomerValidation customerValidation){
        Customer customer = new Customer("",customerValidation.getUsername(),
                customerValidation.getFullName(), customerValidation.getPassword(),
                null, customerValidation.getSecurityQuestions(), null,
                customerValidation.getAadhaar(), customerValidation.getPANPicture(), customerValidation.getPAN(), customerValidation.getPANPicture());
        //Customer existingCx = customerRepository.getByUsername(customer.getUsername());
        //customer.setAccounts(existingCx.getAccounts());
        // TODO
        // Need to use session cookies to identify the customer
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer c = customerRepository.getReferenceById(customer.getId());
        if(c != null){
            customerRepository.save(customer);
            return customer;
        }else return null;
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }
}
