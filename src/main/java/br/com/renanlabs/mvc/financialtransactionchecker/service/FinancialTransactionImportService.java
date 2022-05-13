package br.com.renanlabs.mvc.financialtransactionchecker.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransactionImport;
import br.com.renanlabs.mvc.financialtransactionchecker.model.User;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.FinancialTransactionImportRepository;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.UserRepository;

@Service
public class FinancialTransactionImportService {
	
	@Autowired
	private FinancialTransactionImportRepository transactionImportRepository;
	
	@Autowired
	private UserRepository userRepository;
	

	@Autowired
	private FinancialTransactionService financialTransactionService;

	
	 //building transactions from csv file
	
	public void doImport(MultipartFile file) {
		
		// getting file name and size in MB
        String fileName = file.getOriginalFilename();
        String fileSize = String.valueOf(file.getSize()/(1024*1024));
        
        System.out.println("File name: " + fileName + " | File size (MB): " + fileSize);
        
		TransactionBuilderFromCSV tb = new TransactionBuilderFromCSV(file);
	    
		//checking if transaction date already exist
	    FinancialTransactionImport financialTransactionImportOfDay = findByTransactionDate(tb.findDateFromFinancialTransactionImport());
	   
	    if(financialTransactionImportOfDay != null) {
	    	throw new RuntimeException("A import for date [ " + tb.getDay() +" ] was already registered in database");
	    }
	    
	    //id not, initialize importation routine
	    tb.build();
	    
	    
	    //get current user
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();
	    User user = userRepository.findByUsername(username);
	    
	    //creating financial transaction import and setting the current user
	    FinancialTransactionImport newFinancialTransactionImportOfDay = tb.getFinancialTransactionImport();
	    newFinancialTransactionImportOfDay.setUser(user);
	    newFinancialTransactionImportOfDay = save(newFinancialTransactionImportOfDay);
	   
	    for(FinancialTransaction financialTransaction : tb.getValidFinancialTransactions()) {
	 	   financialTransaction.setFinancialTransactionImport(newFinancialTransactionImportOfDay);
	 	   financialTransactionService.save(financialTransaction); 
	    }
	}
    
	
	public FinancialTransactionImport save(FinancialTransactionImport transactionImport) {
		return transactionImportRepository.save(transactionImport);
	}
	
	public FinancialTransactionImport findById(Long id) {
		return transactionImportRepository.findById(id).orElse(null);
	}
	
	public List<FinancialTransactionImport> findAll(){
		return transactionImportRepository.findAll();
	}
	
	public List<FinancialTransactionImport> findAllByUser(String username){
		return transactionImportRepository.findAllByUser(username);
	}
	
	public FinancialTransactionImport findByTransactionDate(LocalDate date) {
		return transactionImportRepository.findByTransactionDate(date).stream().findFirst().orElse(null);
	}
}
