package com.avanade.testesantander2.util;


import androidx.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class DateUtil {


    private static final DateTimeZone dateTimeZone = DateTimeZone.getDefault();
    //private static final DateTimeZone dateTimeZone = DateTimeZone.forID("Brazil/East");
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter dateTimeFormatterUS = DateTimeFormat.forPattern("yyyy-MM-dd");
    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("dd/MM/yyyy");
    private static final DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");
    @NonNull
    private DateTime data;

    public DateUtil(long timestamp) {
        data = new DateTime(timestamp, DateTimeZone.getDefault());
    }

    public DateUtil(Date date) {
        data = new DateTime(date, dateTimeZone);
    }

    public DateUtil() {
        data = new DateTime(dateTimeZone);
    }

    public static String dateTimeToDate(DateTime dateTime) {
        return dateTime
                .withZone(dateTimeZone)
                .toString(dateFormatter);
    }

    public static String dateTimeToTime(DateTime dateTime) {
        return dateTime
                .withZone(dateTimeZone)
                .toString(timeFormatter);
    }

    public static String dateTimeToString(DateTime dateTime) {
        return dateTime
                .withZone(dateTimeZone)
                .toString(dateTimeFormatter);
    }

    /**
     * Converter de String para DateTime
     *
     * @param stringDate string no formato "dd/MM/yyyy HH:mm:ss"
     * @return DateTime objeto do time DateTime
     */
    public static DateTime stringToDateTime(String stringDate) {
        return dateTimeFormatter
                .parseDateTime(stringDate)
                .withZone(dateTimeZone);
    }

    public static DateTime stringUSToDateTime(String stringDateUS) {
        return dateTimeFormatterUS
                .parseDateTime(stringDateUS)
                .withZone(dateTimeZone);
    }

    @NonNull
    @Override
    public String toString() {
        return data.toString(dateTimeFormatter);
    }
}
