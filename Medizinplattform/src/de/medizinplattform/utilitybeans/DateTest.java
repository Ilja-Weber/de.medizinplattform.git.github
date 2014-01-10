package de.medizinplattform.utilitybeans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
		Date date = new Date();
		String dateAsString = dateFormat.format(date);
		long date_as_int = Long.parseLong(dateAsString);
		System.out.println(date_as_int);
	}

}
