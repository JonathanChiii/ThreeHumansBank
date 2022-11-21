package com.banking.service;

import com.banking.dto.Transfer;

public interface TransactionService {
	
	void doTransfer(Transfer transfer);
}
