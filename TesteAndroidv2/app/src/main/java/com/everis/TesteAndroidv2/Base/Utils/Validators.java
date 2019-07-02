package com.everis.TesteAndroidv2.Base.Utils;

import androidx.core.util.PatternsCompat;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validators {

    public static boolean isValidCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (
                CPF.equals("00000000000") || CPF.equals("11111111111") ||
                        CPF.equals("22222222222") || CPF.equals("33333333333") ||
                        CPF.equals("44444444444") || CPF.equals("55555555555") ||
                        CPF.equals("66666666666") || CPF.equals("77777777777") ||
                        CPF.equals("88888888888") || CPF.equals("99999999999") ||
                        (CPF.length() != 11)
        )
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            return (dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isEmail(final CharSequence email) {
        if (email.toString().isEmpty()){
            return false;
        } else {
            return PatternsCompat.EMAIL_ADDRESS.matcher(email).matches();
        }
    }

    public static boolean isValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;
//        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=\\S+$).{3,}$";
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?=\\S+$).{3,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
