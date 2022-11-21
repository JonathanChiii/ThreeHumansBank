package com.banking.service;

import java.util.List;

import com.banking.model.Beneficiary;

public interface BeneficiaryService {

	Beneficiary update(Beneficiary beneficiary);
	List<Beneficiary> getAllNotApproved();
}
