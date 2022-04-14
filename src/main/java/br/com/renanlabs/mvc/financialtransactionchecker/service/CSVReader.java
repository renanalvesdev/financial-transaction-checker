package br.com.renanlabs.mvc.financialtransactionchecker.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {

	static final String COMMA_DELIMITER = ",";
	
	private File file;

	public CSVReader() {
		// TODO Auto-generated constructor stub
	}

	public CSVReader(File file) {
		this.file = file;
	}

	public List<List<String>> records(){
		
		List<List<String>> records = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(this.file)) {
		    while (scanner.hasNextLine()) {
		        records.add(getRecordFromLine(scanner.nextLine()));
		    }
		} catch (FileNotFoundException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage());
		}
		
		return records;
	}

	private List<String> getRecordFromLine(String line) {
	    List<String> values = new ArrayList<String>();
	    try (Scanner rowScanner = new Scanner(line)) {
	        rowScanner.useDelimiter(COMMA_DELIMITER);
	        while (rowScanner.hasNext()) {
	            values.add(rowScanner.next());
	        }
	    }
	    return values;
	}
}
