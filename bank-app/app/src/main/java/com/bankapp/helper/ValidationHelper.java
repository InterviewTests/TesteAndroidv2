package com.bankapp.helper;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationHelper {

    /**
     * Check correct format of email
     * @param email
     * @return
     */
    public static  boolean isEmailValid(@NonNull String email){
        String regexEmail = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(regexEmail, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Check correct format of cpf
     * @param cpf
     * @return
     */
    public static boolean isValidCpf(@NonNull String cpf){
        int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
        if(!cpf.matches("[0-9]+")) return false;
        if (cpf.length()!=11) return false;

        Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
    }

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    /**
     * Check correct password
     * @param password
     * @return
     */
    public static boolean isValidPassword(@NonNull String password){
        String pattern = "(?=.*[a-z0-9])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,}";
        return password.matches(pattern);
    }

    /**
     * Format account number Mask xxxx/xx.xxxxxx-x
     * @param bankAccount
     * @param agency
     * @return
     */
    public static String formatAccountNumber(@NonNull String bankAccount, @NonNull String agency){
        if (agency.length()<9) String.format("%09d", Long.valueOf(agency));
        return bankAccount + " / " + agency.replaceFirst("(\\d{2})(\\d{6})(\\d{1})", "$1.$2-$3");
    }
}
