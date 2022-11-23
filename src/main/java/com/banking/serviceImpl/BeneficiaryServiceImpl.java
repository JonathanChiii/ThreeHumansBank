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
    public Beneficiary getById(Long id) {
        return beneficiaryRepository.getReferenceById(id);
    }

    @Override
    public List<Beneficiary> getByBeneficiarySource(Customer customer) {
        return beneficiaryRepository.getBeneficiariesByBeneficiarySource(customer);
    }

    @Override
    public List<Beneficiary> getByNotApproved() {
        return beneficiaryRepository.getBeneficiariesByIsApprovedIs(false);
    }

    @Override
    public List<Beneficiary> getByApprovedBy(Staff staff) {
        return beneficiaryRepository.getBeneficiariesByIsApprovedIs(true);
    }

    @Override
    public Beneficiary save(Beneficiary beneficiary) {
        return beneficiaryRepository.save(beneficiary);
    }

    @Override
    public Beneficiary save(BeneficiaryValidation beneficiaryValidation) {
        // ToDo
        return null;
    }

    @Override
    public Beneficiary update(Beneficiary beneficiary) {
        Beneficiary b = beneficiaryRepository.getReferenceById(beneficiary.getId());
        if(b != null) {
            return beneficiaryRepository.save(beneficiary);
        }
        return null;
    }

    @Override
    public void delete(Beneficiary beneficiary) {
        beneficiaryRepository.delete(beneficiary);
    }
}
