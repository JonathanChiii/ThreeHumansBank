package com.banking.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.banking.model.Beneficiary;
import com.banking.repository.BeneficiaryRepository;
import com.banking.service.BeneficiaryService;

@Service
public class BeneficiaryServiceImpl implements BeneficiaryService{

	@Autowired
	private BeneficiaryRepository beneficiaryRepository;
	
	@Override
	public Beneficiary update(Beneficiary beneficiary) {
		Beneficiary b = beneficiaryRepository.getReferenceById(beneficiary.getId());
		if( b!= null ) {
			return beneficiaryRepository.save(beneficiary);
		}
		return null;
	}

	@Override
	public List<Beneficiary> getAllNotApproved() {
		return beneficiaryRepository.getBeneficiaryByisApproved("No");
	}

}
