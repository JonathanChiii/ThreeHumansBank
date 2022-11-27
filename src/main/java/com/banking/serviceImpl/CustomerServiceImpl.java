package com.banking.serviceImpl;

import com.banking.dto.CustomerValidation;
import com.banking.model.Customer;
import com.banking.model.ModelUtility.Status;
import com.banking.repository.CustomerRepository;
import com.banking.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.getReferenceById(id);
    }

    @Override
    public Customer findByUsername(String username) {
        return customerRepository.findCustomerByUsername(username).orElse(null);
    }

    @Override
    public Customer findByAadhaar(String Aadhaar) {
        return customerRepository.findCustomerByAadhaar(Aadhaar).orElse(null);
    }

    @Override
    public Customer findByPAN(String PAN) {
        return customerRepository.findCustomerByPAN(PAN).orElse(null);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        Customer c = customerRepository.getReferenceById(customer.getId());
        if(c != null){
            return customerRepository.save(customer);
        }
        return null;
    }
}
