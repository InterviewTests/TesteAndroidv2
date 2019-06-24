package com.example.bankapp.helper;

import java.text.NumberFormat;

public class MyConveter {

    public static String formatCurrency(Double value) {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        return format.format(value);
    }

    public static String getFormatedInfoBank(String bankAccount, String agency) {
        String textFormated = bankAccount + " / " + formatAgency(agency);
        return textFormated;
    }

    public static String formatAgency(String text) {
        String newText = text.substring(0, 2) + "." + text.substring(2, 8) + "-" + text.substring(8);
        return newText;
    }

    public static String formatDate(String date) {
        String[] arrayDate = date.split("-");
        return arrayDate[2] + "/" + arrayDate[1] + "/" + arrayDate[0];
    }
}
