package com.bilulo.androidtest04.utils;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

    public static boolean isValidCpf(String cpf) {
        if (!isValidString(cpf) || !isFullCpf(cpf)) {
            return false;
        }
        if (cpf.contains(".") && cpf.contains("-")) {
            cpf = cpf.replace(".", "").replace("-", "");
        }
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") || cpf.equals("33333333333") ||
                cpf.equals("44444444444") || cpf.equals("55555555555") ||
                cpf.equals("66666666666") || cpf.equals("77777777777") ||
                cpf.equals("88888888888") || cpf.equals("99999999999") ||
                (cpf.length() != 11))
            return (false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char) (r + 48);

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            return (dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10));
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isValidEmail(String email) {
        return isValidString(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidString(String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean isFullCpf(String cpf) {
        return cpf.matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}-?[0-9]{2}");
    }

    public static boolean hasUppercase(String string) {
        return !string.equals(string.toLowerCase());
    }

    public static boolean hasSpecialCharacter(String string) {
        return !string.matches("[A-Za-z0-9 ]*");
    }

    public static boolean hasAlphanumericCharacter(String string) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        Matcher m = p.matcher(string);
        return m.find();
    }
}
