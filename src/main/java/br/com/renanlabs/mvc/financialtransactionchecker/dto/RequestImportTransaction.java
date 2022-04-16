package br.com.renanlabs.mvc.financialtransactionchecker.dto;

import java.time.LocalDateTime;

import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransaction;

public class RequestImportTransaction {

	private LocalDateTime date;
	
	public FinancialTransaction toImport() {
		
		FinancialTransaction importFile = new FinancialTransaction();
		importFile.setDate(date);
		
		return importFile;
		
	}
	
	
}
