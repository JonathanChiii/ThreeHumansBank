package com.banking.repository;

import java.util.List;

import com.banking.model.Customer;
import com.banking.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Beneficiary;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    List<Beneficiary> getBeneficiariesByBeneficiarySource(Customer customer);
    List<Beneficiary> getAllByIdIsNotNull();
    List<Beneficiary> getBeneficiariesByApprovedBy(Staff staff);
    List<Beneficiary> getBeneficiariesByIsApprovedIs(Boolean isApproved);
}