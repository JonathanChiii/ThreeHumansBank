package com.banking.service;

import com.banking.dto.BeneficiaryValidation;
import com.banking.model.Beneficiary;
import com.banking.model.Customer;
import com.banking.model.Staff;

import java.util.List;

public interface BeneficiaryService {
    Beneficiary getById(Long id);
    List<Beneficiary> getByBeneficiarySource(Customer customer);
    List<Beneficiary> getByNotApproved();
    List<Beneficiary> getByApprovedBy(Staff staff);

    Beneficiary save(Beneficiary beneficiary);
    Beneficiary save(BeneficiaryValidation beneficiaryValidation);
    Beneficiary update(Beneficiary beneficiary);
    void delete(Beneficiary beneficiary);
}
