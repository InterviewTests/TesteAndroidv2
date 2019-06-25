package com.bilulo.androidtest04.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatUtil {
    public static String formatAccountNumber(String bankAccount, String agency) {
        if (ValidationUtil.isValidString(bankAccount) && ValidationUtil.isValidString(agency)) {
            return bankAccount + " / " + setAgencyMask(agency);
        } else
            return null;
    }

    private static String setAgencyMask(String agency) {
        int dashIndex = agency.length() - 2;
        return agency.substring(0, 2) + "." + agency.substring(2, dashIndex) + "-" + agency.substring(dashIndex);
    }

    public static String formatCurrency(BigDecimal value) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String str = null;
        try {
            str = nf.format(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (ValidationUtil.isValidString(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            int index = str.indexOf("$") + 1;
            int lastIndex = str.length();
            if (index > 0 && lastIndex > 0) {
                String strPart1 = str.substring(0, index);
                String strPart2 = str.substring(index, lastIndex);
                str = stringBuilder.append(strPart1).append(" ").append(strPart2).toString();
            }
        }
        return str;
    }

    public static String formatDateFromString(String desiredPattern, String initialPattern, String dateString) {
        Date date = formatStringToDate(initialPattern, dateString);
        return date != null ? formatDate(desiredPattern, date) : "";
    }

    private static Date formatStringToDate(String formatPattern, String date) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern, new Locale("pt", "BR"));
        try {
            return format.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String formatDate(String formatPattern, Date date) {
        SimpleDateFormat format = new SimpleDateFormat(formatPattern, new Locale("pt", "BR"));
        try {
            return format.format(date);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
