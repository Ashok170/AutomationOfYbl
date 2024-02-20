package org.Atyati.YBL;

	import java.time.LocalDate;
	import java.time.Period;
	import java.time.format.DateTimeFormatter;

	public class AgeCalculator {
	    public static void main(String[] args) {
	    
	    }

	    public static int calculateAge(String dobString) {
	        // Parse the date of birth string to a LocalDate object
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        LocalDate dob = LocalDate.parse(dobString, formatter);

	        // Get the current date
	        LocalDate currentDate = LocalDate.now();

	        
	        Period period = Period.between(dob, currentDate);

	       
	        return period.getYears();
	    }
	    
	}


