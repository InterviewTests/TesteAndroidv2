package com.santander.ian.santanderauth.Utils;

import java.math.BigDecimal;
import java.text.NumberFormat;

public class Auxiliar {

    public static boolean strong_password(String senha) {
        if (senha.length() < 3) return false;

        boolean achouNumero = false;
        boolean achouMaiuscula = false;
        boolean achouMinuscula = false;
        boolean achouSimbolo = false;
        for (char c : senha.toCharArray()) {
            if (c >= '0' && c <= '9') {
                achouNumero = true;
            } else if (c >= 'A' && c <= 'Z') {
                achouMaiuscula = true;
            } else if (c >= 'a' && c <= 'z') {
                achouMinuscula = true;
            } else {
                achouSimbolo = true;
            }
        }

        boolean achouAlfanumerico = achouNumero || achouMinuscula;

        return achouAlfanumerico && achouMaiuscula && achouSimbolo;
    }

    public static String convert_to_currency(String Number){

        BigDecimal valor = new BigDecimal (Number);
        NumberFormat nf = NumberFormat.getCurrencyInstance();

        return nf.format (valor);
    }

    public static String convert_to_currency(BigDecimal Number){

        NumberFormat nf = NumberFormat.getCurrencyInstance();

        return nf.format (Number);
    }

    public static String format_date(String date_json) {
        String array[] = new String[3];
        array = date_json.split("-");

        StringBuilder new_date = new StringBuilder();
        new_date.append(array[2]);
        new_date.append("/");
        new_date.append(array[1]);
        new_date.append("/");
        new_date.append(array[0]);


        return new_date.toString();
    }
}
