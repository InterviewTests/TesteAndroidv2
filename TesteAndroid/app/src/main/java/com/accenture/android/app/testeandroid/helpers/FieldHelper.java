package com.accenture.android.app.testeandroid.helpers;

import java.util.InputMismatchException;

/**
 * Created by Denis Magno on 10/07/2020.
 * denis_magno16@hotmail.com
 */
public class FieldHelper {
    public static boolean isCPF(String CPF) {
        // Retira qualquer caracter que foi digitado pelo usuário é válido em um CPF
        CPF = CPF.replaceAll("[.]", "").replaceAll("[-]", "")
                .replaceAll("[ ]", "");

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            // Calculo do 1º. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2º. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isEmail(String email) {
        return email.matches("([a-z0-9-._]+)@([a-z]+).([a-z]+).([a-z]+)");
    }

    public static boolean isPassword(String password) {
        return password.matches("^(?=.*?[A-Z])(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{3,}$");
    }
}
