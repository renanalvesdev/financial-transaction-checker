package br.com.renanlabs.mvc.financialtransactionchecker.dto;

import java.time.LocalDateTime;

import br.com.renanlabs.mvc.financialtransactionchecker.model.ImportTransaction;

public class RequestImportTransaction {

	private LocalDateTime date;
	
	public ImportTransaction toImport() {
		
		ImportTransaction importFile = new ImportTransaction();
		importFile.setDate(date);
		
		return importFile;
		
	}
	
	
}
