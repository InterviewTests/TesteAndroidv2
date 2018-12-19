package com.example.jcsantos.santanderteste.Components.Utils;

import java.util.regex.Pattern;

public class ItemsValidate {
    private static final int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    public boolean isValid(String cpfEmail) {
        return (isValidEmail(cpfEmail) || isValidCPF(cpfEmail));
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
        cpf = cpf.trim().replace(".", "").replace("-", "");
        if ((cpf == null) || (cpf.length() != 11)) return false;

        for (int j = 0; j < 10; j++)
            if (padLeft(Integer.toString(j), Character.forDigit(j, 10)).equals(cpf))
                return false;

        Integer digito1 = calcularDigito(cpf.substring(0, 9), pesoCPF);
        Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, pesoCPF);
        return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
    }

    private static boolean isValidEmail(String email) {
        if (email == null)
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean checkPassword(String password) {
        System.out.println("::::::::::::::>" + password);
        Pattern number = Pattern.compile("[0-9 ]");
        Pattern uperCase = Pattern.compile("[A-Z ]");
        Pattern characters = Pattern.compile("[!@#$%&*_]");

        if (!number.matcher(password).find()) {
            return false;
        } else if (!uperCase.matcher(password).find()) {
            return false;
        } else if (!characters.matcher(password).find()) {
            return false;
        } else {
            return true;
        }
    }
}