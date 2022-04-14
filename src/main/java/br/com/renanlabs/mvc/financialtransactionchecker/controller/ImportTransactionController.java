package br.com.renanlabs.mvc.financialtransactionchecker.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.renanlabs.mvc.financialtransactionchecker.dto.RequestImportTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.model.ImportTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.ImportTransactionRepository;

@Controller
@RequestMapping("importTransaction")
public class ImportTransactionController {
	
	@Autowired
	private ImportTransactionRepository importTransactionRepository;

	@GetMapping("form")
	public String form(RequestImportTransaction requisicao) {
		return "importTransaction/form";
	}
	
	@PostMapping("new")
	public String novo(@Valid RequestImportTransaction requisicao, BindingResult result) {
		
		if(result.hasErrors()) {
			return "importTransaction/form";
		}
		
		ImportTransaction importTransactionFile = requisicao.toImport();
		importTransactionRepository.save(importTransactionFile);
		
		return "redirect:/home";
	}
	
}
