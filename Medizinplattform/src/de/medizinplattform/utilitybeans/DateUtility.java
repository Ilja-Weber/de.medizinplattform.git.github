package de.medizinplattform.utilitybeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	
	//Logic
	public static long calculateYear(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		String date_as_string = dateFormat.format(date);
		long date_as_long = Long.parseLong(date_as_string);
		return date_as_long;
	}
	
	//Logic
	public static long calculateMonth(){
		DateFormat dateFormat = new SimpleDateFormat("MM");
		Date date = new Date();
		String date_as_string = dateFormat.format(date);
		long date_as_long = Long.parseLong(date_as_string);
		return date_as_long;
	}
		
	//Logic
	public static long calculateDay(){
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		String date_as_string = dateFormat.format(date);
		long date_as_long = Long.parseLong(date_as_string);
		return date_as_long;
	}
}
