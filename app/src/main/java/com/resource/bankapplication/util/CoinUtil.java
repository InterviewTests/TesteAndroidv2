package com.resource.bankapplication.util;

import java.text.NumberFormat;
import java.util.Locale;

public class CoinUtil {
    public static String formatReal(Double coin){
        NumberFormat money = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        String format = money.format(coin);
        return format.contains("-") ? format.substring(1,3)+"-"+format.substring(3):
                format.contains("(")? format.replace("(", "")
                        .replace(")", "")
                        .replace("$","$-"):format;
    }
}
