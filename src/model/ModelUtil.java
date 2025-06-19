package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

abstract class ModelUtil {
	static LocalDate convertStringToDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(dateStr, formatter);
		return date;
	}
}
