package com.gft.testegft.statements.utils;

public class DateFormatter {
    public static String  formatDate(String date) {
        String[] array = date.split("-");
        return array[2] + "/" + array[1] + "/" + array[0];
    }
}
