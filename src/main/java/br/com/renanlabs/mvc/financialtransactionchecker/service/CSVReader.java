package br.com.renanlabs.mvc.financialtransactionchecker.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.web.multipart.MultipartFile;

public class CSVReader {

	static final String COMMA_DELIMITER = ",";
	
	private InputStream file;

	public CSVReader() {
		// TODO Auto-generated constructor stub
	}

	public CSVReader(InputStream file) {
		this.file = file;
	}

	public CSVReader(MultipartFile file) {
		try {
			this.file = file.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<List<String>> records(){
		
		List<List<String>> records = new ArrayList<>();
		
		try (Scanner scanner = new Scanner(this.file)) {
		    while (scanner.hasNextLine()) {
		        records.add(getRecordFromLine(scanner.nextLine()));
		    }
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
