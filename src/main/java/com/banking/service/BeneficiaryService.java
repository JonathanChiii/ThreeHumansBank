package com.banking.service;

import com.banking.dto.BeneficiaryValidation;
import com.banking.model.Beneficiary;
import com.banking.model.Customer;
import com.banking.model.Staff;

import java.util.List;

public interface BeneficiaryService {
    Beneficiary findById(Long id);
    List<Beneficiary> findByBeneficiarySource(Customer customer);
    List<Beneficiary> findByNotApproved();
    List<Beneficiary> findByApprovedBy(Staff staff);
    Beneficiary save(Beneficiary beneficiary);
    Beneficiary update(Beneficiary beneficiary);
}
