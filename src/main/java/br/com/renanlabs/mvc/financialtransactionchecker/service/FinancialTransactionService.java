package br.com.renanlabs.mvc.financialtransactionchecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.FinancialTransactionRepository;

@Service
public class FinancialTransactionService {
	
	@Autowired
	private FinancialTransactionRepository financialTransactionRepository;
	
	public FinancialTransaction save(FinancialTransaction financialTransaction) {
		return financialTransactionRepository.save(financialTransaction);
	}
	
	
}
