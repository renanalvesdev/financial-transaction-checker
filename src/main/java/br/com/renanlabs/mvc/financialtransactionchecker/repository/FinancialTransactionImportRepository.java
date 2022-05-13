package br.com.renanlabs.mvc.financialtransactionchecker.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransactionImport;

@Repository
public interface FinancialTransactionImportRepository extends JpaRepository<FinancialTransactionImport, Long> {

	Optional<FinancialTransactionImport> findByTransactionDate(LocalDate date);
	
	@Query("select f from FinancialTransactionImport f join f.user u where u.username = :username")
	List<FinancialTransactionImport> findAllByUser(@Param("username") String username);
} 