package com.banking.serviceImpl;

import com.banking.dto.BeneficiaryValidation;
import com.banking.model.Beneficiary;
import com.banking.model.Customer;
import com.banking.model.Staff;
import com.banking.repository.BeneficiaryRepository;
import com.banking.service.BeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService {

    @Autowired
    private BeneficiaryRepository beneficiaryRepository;

    @Override
    public Beneficiary findById(Long id) {
        return beneficiaryRepository.getReferenceById(id);
    }

    @Override
    public List<Beneficiary> findByBeneficiarySource(Customer customer) {
        return beneficiaryRepository.findBeneficiariesByBeneficiarySource(customer);
    }

    @Override
    public List<Beneficiary> findByNotApproved() {
        return beneficiaryRepository.findBeneficiariesByIsApprovedIs(false);
    }

    @Override
    public List<Beneficiary> findByApprovedBy(Staff staff) {
        return beneficiaryRepository.findBeneficiariesByApprovedBy(staff);
    }

    @Override
    public Beneficiary save(Beneficiary beneficiary) {
            return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public Beneficiary update(Beneficiary beneficiary) {
        Beneficiary b = beneficiaryRepository.getReferenceById(beneficiary.getId());
        if(b != null) {
            return beneficiaryRepository.save(beneficiary);
        }
        return null;
    }
}
