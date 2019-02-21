package com.avanade.testesantander2.util;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.regex.Pattern;

public abstract class CpfUtil {

    public static final String TAG = CpfUtil.class.getName();

    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] pesoCNPJ = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

    public static TextWatcher insert(final EditText editText) {

        return new TextWatcher() {

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (isValid(s.toString()))
                    editText.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
                else
                    editText.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {
            }
        };
    }

    public static boolean isValid(String cpfCnpj) {
        return (isValidCPF(cpfCnpj) || isValidCNPJ(cpfCnpj));
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
            digito = Integer.parseInt(str.substring(indice, indice + 1));
            soma += digito * peso[peso.length - str.length() + indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    private static String padLeft(String text, char character) {
        return String.format("%11s", text).replace(' ', character);
    }

    private static boolean isValidCPF(String cpf) {

        String cpfPattern_1 = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Pattern pattern_1 = Pattern.compile(cpfPattern_1);
        boolean regexCPF_1 = pattern_1.matcher(cpf).matches();

        String cpfPattern_2 = "^\\d{11}$";
        Pattern pattern_2 = Pattern.compile(cpfPattern_2);
        boolean regexCPF_2 = pattern_2.matcher(cpf).matches();

        if( ! (regexCPF_1 || regexCPF_2))
            return false;


        cpf = cpf.trim().replace(".", "").replace("-", "");
        if ((cpf == null) || (cpf.length() != 11)) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    private static boolean isValidCNPJ(String cnpj) {

        String cnpjPattern_1 = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$";
        Pattern pattern_1 = Pattern.compile(cnpjPattern_1);
        boolean regexCNPJ_1 = pattern_1.matcher(cnpj).matches();

        String cnpjPattern_2 = "^\\d{11}$";
        Pattern pattern_2 = Pattern.compile(cnpjPattern_2);
        boolean regexCNPJ_2 = pattern_2.matcher(cnpj).matches();

        if(! (regexCNPJ_1 || regexCNPJ_2))
            return false;


        cnpj = cnpj.trim().replace(".", "").replace("-", "");
        if ((cnpj == null) || (cnpj.length() != 14)) return false;

        Integer digito1 = calcularDigito(cnpj.substring(0, 12), pesoCNPJ);
        Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, pesoCNPJ);
        return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
    }

}
