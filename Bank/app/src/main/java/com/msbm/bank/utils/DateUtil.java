package com.msbm.bank.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String formatDate(String pattern, String date) {
        String formattedDate;
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);

        try {
            Date newDate = new SimpleDateFormat("yyyy-mm-dd").parse(date);
            formattedDate = formatter.format(newDate);
        } catch (ParseException e) {
            return date;
        }

        return formattedDate;
    }
}
