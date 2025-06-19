package base;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Base {
	protected void exit(){
        System.out.println("Exiting ...");
        System.exit(0);
    }
    protected LocalDate convertStringToDate(String dateStr) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(dateStr, formatter);
		return date;
	}
}
