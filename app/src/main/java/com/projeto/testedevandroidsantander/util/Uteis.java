package com.projeto.testedevandroidsantander.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Uteis {

    private static final String DATE_FORMAT = "dd/MM/yyy";

    public static String formatDataParaBr(Date data) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
        return dateFormat.format(data);
    }

    public static String formatCurrencyBr(Double valor) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(valor);
    }

    public static String addMaskContaAgencia(final String textoAFormatar, final String mask) {
        String formatado = "";
        int i = 0;
        for (char m : mask.toCharArray()) {
            if (m != '#') {
                formatado += m;
                continue;
            }
            try {
                formatado += textoAFormatar.charAt(i);
            } catch (Exception e) {
                break;
            }
            i++;
        }
        return formatado;
    }

    public static boolean isCPF(String CPF) {
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
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (CPF.charAt(i) - 48);
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
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char) (r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static boolean isEmailValido(String email) {
        return email == null ? false : android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isSenhaValida(String senha) {
        if (senha.length() >= 2) {
            Pattern alfabetoMinusculo = Pattern.compile("[a-z]");
            Pattern alfabetoMaiusculo = Pattern.compile("[A-Z]");
            Pattern caracteresEspeciais = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
            Pattern numeros = Pattern.compile("[0-9]+");

            Matcher temLetraMinuscula = alfabetoMinusculo.matcher(senha);
            Matcher temLetraMaiuscula = alfabetoMaiusculo.matcher(senha);
            Matcher temCaracterEspecial = caracteresEspeciais.matcher(senha);
            Matcher temNumero = numeros.matcher(senha);

            return temLetraMinuscula.find() && temLetraMaiuscula.find() && temCaracterEspecial.find() && temNumero.find();
        }

        return false;
    }
}
