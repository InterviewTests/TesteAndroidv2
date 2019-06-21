package com.resource.bankapplication.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTypeDeserializer {
    public static String formatDate(String date) {
        String result = "";
        try {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                format.setLenient(false);
                Date d = format.parse(date);
                format = new SimpleDateFormat("dd/MM/yyyy");
                result = format.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
