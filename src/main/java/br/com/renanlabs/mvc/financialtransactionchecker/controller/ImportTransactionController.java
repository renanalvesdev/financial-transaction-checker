package br.com.renanlabs.mvc.financialtransactionchecker.controller;

import java.util.List;

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
import br.com.renanlabs.mvc.financialtransactionchecker.model.ImportTransaction;
import br.com.renanlabs.mvc.financialtransactionchecker.repository.ImportTransactionRepository;
import br.com.renanlabs.mvc.financialtransactionchecker.service.CSVReader;

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
	        
	        List<List<String>> records = new CSVReader(file).records();
	        System.out.println("Lines of file: "); 
	        records.forEach(r -> System.out.println(r));

	        // return success response
	        attributes.addFlashAttribute("message", "You successfully uploaded " + fileName + '!');

	        return "redirect:/importTransaction/form";
	    }

	}

