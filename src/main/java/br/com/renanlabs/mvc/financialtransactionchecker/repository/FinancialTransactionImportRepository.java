package br.com.renanlabs.mvc.financialtransactionchecker.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransactionImport;

@Repository
public interface FinancialTransactionImportRepository extends JpaRepository<FinancialTransactionImport, Long> {

	List<FinancialTransactionImport> findByTransactionDate(LocalDate date);
	
}