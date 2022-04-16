package br.com.renanlabs.mvc.financialtransactionchecker.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Date {

	
	public static LocalDate toLocalDate(String isoDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDate date = LocalDate.parse(isoDate, formatter);
		return date;
	}
	
	public static LocalDateTime toLocalDateTime(String isoDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(isoDate, formatter);
		return date;
	}
}
