package com.avanade.testesantander2.util;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public abstract class MonetarioUtil {

    public static String TAG = MonetarioUtil.class.getSimpleName();

    public static String formataMoedaPtBr(double valor) {
        String formatado = "";
        double value = valor;
        if(valor < 0) {
            formatado += "-";
            value *= (-1);
        }

        //BigDecimal moeda = new BigDecimal ("12000000.12");
        BigDecimal moeda = new BigDecimal(value);
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        formatado += nf.format(moeda);
        //System.out.println(formatado); //O resultado é R$ 12.000.000,12
        Log.i(TAG, "Formata Moeda (" + valor + ") para (" + formatado + ")");
        return formatado;
    }


    public static String formataNumeroPtBr(double valor) {
        //BigDecimal moeda = new BigDecimal ("12000000.12");
        BigDecimal numero = new BigDecimal(valor);
        NumberFormat generalNumber = NumberFormat.getInstance();
        String formatado = generalNumber.format(numero);
        //System.out.println(formatado); //O resultado é R$ 12.000.000,12
        Log.i(TAG, "Formata Moeda (" + valor + ") para (" + formatado + ")");
        return formatado;
    }

    public static String formataMoedaPtBrDecimal(double valor) {
        if (valor < 0)
            return new DecimalFormat("-R$#.###.###.###.###.##0,00").format(valor).toString();
        return new DecimalFormat("R$#.###.###.###.###.##0,00").format(valor).toString();
    }

}



