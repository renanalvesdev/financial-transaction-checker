package br.com.renanlabs.mvc.financialtransactionchecker.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransactionImport;
import br.com.renanlabs.mvc.financialtransactionchecker.util.Date;

public class TransactionBuilderFromCSV {

	private static final int ORIGIN_BANK_POSITION = 0;
	private static final int ORIGIN_AGENCY_POSITION = 1;
	private static final int ORIGIN_ACCOUNT_POSITION = 2;

	private static final int DESTINATION_BANK_POSITION = 3;
	private static final int DESTINATION_AGENCY_POSITION = 4;
	private static final int DESTINATION_ACCOUNT_POSITION = 5;

	private static final int AMOUNT_POSITION = 6;
	private static final int DATE_POSITION = 7;

	private LocalDate day;

	private FinancialTransactionImport financialTransactionImport;

	private List<FinancialTransaction> validFinancialTransactions;
	
	private List<List<String>> records;

	public TransactionBuilderFromCSV(MultipartFile file) {
		try {
			 records = new CSVReader(file.getInputStream()).records();
			financialTransactionImport = new FinancialTransactionImport(file.getSize(), file.getOriginalFilename());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void build() {

		financialTransactionImport.setTransactionDate(day);
		financialTransactionImport.setDate(LocalDateTime.now());
		
		validFinancialTransactions = records.stream()
				.filter(r -> isTransactionValid(r))
				.map(r -> CSVToFinancialTransaction(r))
				.collect(Collectors.toList());
	}

	// return true if validations are true for set transaction as valid
	private boolean isTransactionValid(List<String> transaction) {
		
		if (!isValidDate(transaction.get(DATE_POSITION)) && isAllFieldsFilled(transaction)) {
			System.out.println("Invalid -> " + transaction);
			return false;
		}
		
		return true;
	}

	// valid date if is same as day of transactions import(filled)
	private boolean isValidDate(String date) {
		return (Date.toLocalDate(date).equals(day));
	}

	// fields are filled if none is null or empty
	private boolean isAllFieldsFilled(List<String> transaction) {
		return transaction.stream().noneMatch(t -> (t.equals(null) || t.isBlank() || t.isEmpty()));
	}
	
	private FinancialTransaction CSVToFinancialTransaction(List<String> transaction){
		FinancialTransaction financialTransaction = new FinancialTransaction();

		financialTransaction.setOriginAccount(transaction.get(ORIGIN_ACCOUNT_POSITION));
		financialTransaction.setOriginAgency(transaction.get(ORIGIN_AGENCY_POSITION));
		financialTransaction.setOriginBank(transaction.get(ORIGIN_BANK_POSITION));
		
		financialTransaction.setDestinationAccount(transaction.get(DESTINATION_ACCOUNT_POSITION));
		financialTransaction.setDestinationAgency(transaction.get(DESTINATION_AGENCY_POSITION));
		financialTransaction.setDestinationBank(transaction.get(DESTINATION_BANK_POSITION));
		
		financialTransaction.setAmount(Double.valueOf(transaction.get(AMOUNT_POSITION)));
		financialTransaction.setDate(Date.toLocalDateTime(transaction.get(DATE_POSITION)));
		
		return financialTransaction;
	}

	public LocalDate findDateFromFinancialTransactionImport() {

		day = records
				.stream()
				.filter(r -> isAllFieldsFilled(r))
				.findFirst()
				.map(r -> Date.toLocalDate(r.get(DATE_POSITION)))
				.orElseThrow(() -> new RuntimeException("Could not extract transactions day because theres no valid registers in CSV"));
		
		System.out.println(day);
		return day;
	}

	public FinancialTransactionImport getFinancialTransactionImport() {
		return financialTransactionImport;
	}

	public List<FinancialTransaction> getValidFinancialTransactions() {
		return validFinancialTransactions;
	}

	public LocalDate getDay() {
		return day;
	}
	
	
}
