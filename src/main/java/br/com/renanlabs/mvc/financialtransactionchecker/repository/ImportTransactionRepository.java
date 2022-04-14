package br.com.renanlabs.mvc.financialtransactionchecker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.renanlabs.mvc.financialtransactionchecker.model.ImportTransaction;

@Repository
public interface ImportTransactionRepository extends JpaRepository<ImportTransaction, Long> {

	
}