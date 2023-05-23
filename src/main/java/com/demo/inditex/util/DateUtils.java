package com.demo.inditex.util;

import com.demo.inditex.price.infraestructure.Exceptions.ParseDateException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static LocalDateTime parseRequestStringToDate(String date) throws ParseDateException {
        try {
            DateTimeFormatter isoFormat = DateTimeFormatter.ISO_DATE_TIME;
            return LocalDateTime.parse(date,isoFormat);
        }catch (Exception e){
            throw new ParseDateException(String.format(ErrorDictionary.PARSE_DATE_ERROR_MESSAGE, date));
        }

    }
}
