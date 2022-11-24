package com.banking.repository;

import java.util.List;
import java.util.Optional;

import com.banking.model.Customer;
import com.banking.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import com.banking.model.Beneficiary;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    Optional<Beneficiary> findBeneficiariesByUsername(String username);
    List<Beneficiary> findBeneficiariesByBeneficiarySource(Customer customer);
    List<Beneficiary> findBeneficiariesByApprovedBy(Staff staff);
    List<Beneficiary> findBeneficiariesByIsApprovedIs(Boolean isApproved);
}