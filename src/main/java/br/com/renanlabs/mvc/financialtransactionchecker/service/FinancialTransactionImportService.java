package br.com.renanlabs.mvc.financialtransactionchecker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransactionImport;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.FinancialTransactionImportRepository;

@Service
public class FinancialTransactionImportService {
	
	@Autowired
	private FinancialTransactionImportRepository transactionImportRepository;
	
	public void save(FinancialTransactionImport transactionImport) {
		transactionImportRepository.save(transactionImport);
	}
	
	public FinancialTransactionImport findById(Long id) {
		return transactionImportRepository.findById(id).orElse(null);
	}
	
	public List<FinancialTransactionImport> findAll(){
		return transactionImportRepository.findAll();
	}
	
	public List<FinancialTransactionImport> findByTransactionDate(LocalDate date) {
		return transactionImportRepository.findByTransactionDate(date);
	}
}
