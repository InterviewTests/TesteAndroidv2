package ssilvalucas.testeandroidv2.util;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatUtil {

    public static String formatBankAccount(String agency, String bank){
        String out = bank + " / " + agency.substring(0, 2) + "." + agency.substring(2, agency.length() - 1) + "-" + agency.substring(agency.length() - 1);
        return out;
    }

    public static String formatBalance(double balance){
        return NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(balance);
    }

}
