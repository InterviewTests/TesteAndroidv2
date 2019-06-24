package com.resource.bankapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeDeserializer {

    public static final String PATTERN_US = "yyyy-MM-dd";
    public static final String PATTERN_REAL = "dd/MM/yyyy";

    public static String formatDate(String date) {
        String result = "";
        try {
                SimpleDateFormat format = new SimpleDateFormat(PATTERN_US);
                format.setLenient(false);
                Date d = format.parse(date);
                format = new SimpleDateFormat(PATTERN_REAL);
                result = format.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
