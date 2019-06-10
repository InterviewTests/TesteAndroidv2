package br.com.douglas.fukuhara.bank.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class FormaterUtils {

    private static String bigdecimalToCurrency(BigDecimal balance) {
        NumberFormat numberFormatter;
        numberFormatter = NumberFormat.getInstance(new Locale("pt", "BR"));
        numberFormatter.setRoundingMode(RoundingMode.HALF_UP);
        numberFormatter.setMaximumFractionDigits(2);
        numberFormatter.setMinimumFractionDigits(2);
        return numberFormatter.format(balance);
    }

    public static String formatAgencyWithMask(String agency) {
        return String.format("%s.%s-%s",
                agency.substring(0, 2),
                agency.substring(2, 8),
                agency.substring(8, agency.length()));
    }

    public static String includeCurrencyInValue(BigDecimal balance) {
        String formattedBalance = bigdecimalToCurrency(balance);
        if (formattedBalance.contains("-")) {
            return String.format("- R$%s", formattedBalance.replace("-", ""));
        }
        return String.format("R$%s", formattedBalance);
    }

    public static String formatBankAccountAndAgency(String agency, String bankAccount) {
        return String.format("%s / %s", bankAccount, agency);
    }

    public static String formatDateToBrazilian(String dateToBeFormatted) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date data = null;
        try {
            data = formato.parse(dateToBeFormatted);
            formato.applyPattern("dd/MM/yyyy");
            return formato.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateToBeFormatted;
    }
}
