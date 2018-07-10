package br.com.meuBanco.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by F112861 on 03/01/2018.
 */
public class ConversaoUtils {

    private ConversaoUtils(){}

    public static LocalDate convertDateToLocalTime(Date dateToConvert) {

        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

    }

    public static LocalDate convertStringToLocalDate(String dateToConvert) {

	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    
	    return LocalDate.parse(dateToConvert, formatter);
    }
}
