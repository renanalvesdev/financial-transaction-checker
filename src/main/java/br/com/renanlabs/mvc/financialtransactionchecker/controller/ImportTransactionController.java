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
	        }

	        try {
	        	//building transactions from csv file
		        transactionImportService.doImport(file);
		        // return success response
		        attributes.addFlashAttribute("message", "File sucessful uploaded !!");

			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
	       
	        return "redirect:/importTransaction/form";
	    }

	}

