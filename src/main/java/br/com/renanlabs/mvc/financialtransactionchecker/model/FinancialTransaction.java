package br.com.renanlabs.mvc.financialtransactionchecker.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FinancialTransaction {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String originBank;
	private String originAgency;
	private String originAccount;
	
	private String destinationBank;
	private String destinationAgency;
	private String destinationAccount;
	
	private Double amount;
	
	private LocalDateTime date;

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

	public String getOriginBank() {
		return originBank;
	}

	public void setOriginBank(String originBank) {
		this.originBank = originBank;
	}

	public String getOriginAgency() {
		return originAgency;
	}

	public void setOriginAgency(String originAgency) {
		this.originAgency = originAgency;
	}

	public String getOriginAccount() {
		return originAccount;
	}

	public void setOriginAccount(String originAccount) {
		this.originAccount = originAccount;
	}

	public String getDestinationBank() {
		return destinationBank;
	}

	public void setDestinationBank(String destinationBank) {
		this.destinationBank = destinationBank;
	}

	public String getDestinationAgency() {
		return destinationAgency;
	}

	public void setDestinationAgency(String destinationAgency) {
		this.destinationAgency = destinationAgency;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}

	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	
	
	
}
