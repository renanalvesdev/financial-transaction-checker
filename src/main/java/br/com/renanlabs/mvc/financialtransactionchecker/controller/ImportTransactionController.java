package br.com.renanlabs.mvc.financialtransactionchecker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.renanlabs.mvc.financialtransactionchecker.dto.RequestImportTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.model.FinancialTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.FinancialTransactionRepository;
import br.com.renanlabs.mvc.financialtransactionchecker.service.FinancialTransactionImportService;
import br.com.renanlabs.mvc.financialtransactionchecker.service.TransactionBuilderFromCSV;

@Controller
@RequestMapping("importTransaction")
public class ImportTransactionController {
	
	@Autowired
	private FinancialTransactionRepository importTransactionRepository;
	

	@Autowired
	private FinancialTransactionImportService transactionImportService;


	@GetMapping("form")
	public String form(RequestImportTransaction requisicao) {
		return "importTransaction/form";
	}
	
	@PostMapping("new")
	public String novo(@Valid RequestImportTransaction requisicao, BindingResult result) {
		
		if(result.hasErrors()) {
			return "importTransaction/form";
		}
		
		FinancialTransaction importTransactionFile = requisicao.toImport();
		importTransactionRepository.save(importTransactionFile);
		
		return "redirect:/home";
	}
	
	 @PostMapping("upload")
	    public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) {

	        // check if file is empty
	        if (file.isEmpty()) {
	            attributes.addFlashAttribute("message", "Please select a file to upload.");
	            return "redirect:/";
	        }

	        // getting file name and size in MB
	        String fileName = file.getOriginalFilename();
	        String fileSize = String.valueOf(file.getSize()/(1024*1024));
	        
	        System.out.println("File name: " + fileName + " | File size (MB): " + fileSize);
	        
	        //building transactions from csv file
	        TransactionBuilderFromCSV tb = new TransactionBuilderFromCSV(file);
	        
	       if( transactionImportService.findByTransactionDate(tb.findDateFromFinancialTransactionImport()).isEmpty()) {
	    	   tb.build();
	    	   System.out.println("oi" + tb.getValidFinancialTransactions());
	       }
	        
	        System.out.println("Import information: " + tb.getFinancialTransactionImport());
	        System.out.println("Transactions information : " + tb.getValidFinancialTransactions() );
	        
	        // return success response
	        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

	        return "redirect:/importTransaction/form";
	    }

	}

