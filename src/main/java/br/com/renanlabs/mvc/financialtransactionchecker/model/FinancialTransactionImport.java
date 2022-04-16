package br.com.renanlabs.mvc.financialtransactionchecker.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FinancialTransactionImport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// date and time when importation was done.
	private LocalDateTime date;

	private Long fileSize;

	private LocalDate transactionDate;

	private String fileName;

	public FinancialTransactionImport() {

	}

	public FinancialTransactionImport(Long fileSize, String fileName) {
		super();
		this.fileSize = fileSize;
		this.fileName = fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Importation date: " + this.date + " | Transactions day: " + this.transactionDate + " | File name: "
				+ this.fileName + " | File size: " + this.fileSize;
	}
}
