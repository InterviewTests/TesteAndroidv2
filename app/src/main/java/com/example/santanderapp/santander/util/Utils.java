package com.example.santanderapp.santander.util;

import android.content.Context;
import android.net.ConnectivityManager;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isEmailValid(String email) {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches())
            return true;
        else
            return false;
    }

    public static boolean isCpfValid(String cpf) {
        cpf = cpf.replace(".", "").replace("-", "").trim();
        if (cpf == null || cpf.length() != 11)
            return false;

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) { // CPF não possui somente números
            return false;
        }

        return calcDigVerif(cpf.substring(0, 9)).equals(cpf.substring(9, 11));
    }

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        if (soma % 11 == 0 | soma % 11 == 1)
            primDig = new Integer(0);
        else
            primDig = new Integer(11 - (soma % 11));

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++)
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1)
            segDig = new Integer(0);
        else
            segDig = new Integer(11 - (soma % 11));

        return primDig.toString() + segDig.toString();
    }

    public static boolean isPasswordValid(String senha) {
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
        return achouNumero && achouMaiuscula && achouMinuscula && achouSimbolo;
    }

    public static boolean isConected(Context cont) {
        ConnectivityManager conmag = (ConnectivityManager) cont.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (conmag != null) {
            conmag.getActiveNetworkInfo();

            //Verifica internet pela WIFI
            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return true;
            }

            //Verifica se tem internet móvel
            if (conmag.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return true;
            }
        }

        return false;
    }

    public static String formatRealHeader(String value) {

        String comPonto = value.replaceAll("[^0-9]", "");
        StringBuilder stringBuilder = new StringBuilder(comPonto);
        stringBuilder.insert(comPonto.length() - 2, '.');
        comPonto = stringBuilder.toString();
        BigDecimal valor = new BigDecimal(comPonto);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(valor);
    }

    public static String formatReal(String value) {

        Integer pos = value.length()-(value.indexOf(".")+1);
        String comPonto = value.replaceAll("[^0-9]", "");
        StringBuilder stringBuilder = new StringBuilder(comPonto);
        stringBuilder.insert(comPonto.length() - pos, '.');
        comPonto = stringBuilder.toString();
        BigDecimal valor = new BigDecimal(comPonto);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        if(value.contains("-")){
            valor = valor.negate();
        }
        return nf.format(valor);
    }

    public static String formatAccount(String value) {

        String temp = value.replaceAll("[^0-9]", "");
        StringBuilder stringBuilder = new StringBuilder(temp);
        stringBuilder.insert(temp.length() - 1, '-');
        stringBuilder.insert(2, '.');
        return stringBuilder.toString();
    }

    public static String convertData(String date) throws ParseException {
        String dataBR = date;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date data = format.parse(dataBR);
        format.applyPattern("dd/MM/yyyy");
        String dateFormated = format.format(data);
        return dateFormated;
    }
}
