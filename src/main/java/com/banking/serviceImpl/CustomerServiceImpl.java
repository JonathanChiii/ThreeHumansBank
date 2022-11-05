package com.banking.serviceImpl;

import com.banking.dto.CustomerValidation;
import com.banking.dto.UserLogin;
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
    public Customer findById(Long id) {
        return customerRepository.getReferenceById(id);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findCustomerByUsername(username);
    }

    @Override
    public Customer findByAadhaar(String Aadhaar) {
        return customerRepository.findCustomerByAadhaar(Aadhaar);
    }

    @Override
    public Customer findByPAN(String PAN) {
        return customerRepository.findCustomerByPAN(PAN);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void save(CustomerValidation customerValidation){
        Customer customer = new Customer(Long.valueOf(0),customerValidation.getUsername(),
                customerValidation.getFullName(), customerValidation.getPassword(),
                null, customerValidation.getSecurityQuestions(), customerValidation.getBeneficiaries(), null,
                customerValidation.getAadhar(), customerValidation.getPanPicture(), customerValidation.getPan(), customerValidation.getPanPicture());
        //Customer existingCx = customerRepository.findByUsername(customer.getUsername());
        //customer.setAccounts(existingCx.getAccounts());
        // TODO
        // Need to use session cookies to identify the customer
        customerRepository.save(customer);
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
