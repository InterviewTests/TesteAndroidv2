package com.br.web.glix.interviewgiovanipaleologo.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.util.Patterns;

import java.util.InputMismatchException;
import java.util.regex.Pattern;

public class Utils {

    public static int getSoftButtonsBarSizePort(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int usableHeight = metrics.heightPixels;
        activity.getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        int realHeight = metrics.heightPixels;
        if (realHeight > usableHeight)
            return realHeight - usableHeight;
        else
            return 0;
    }

    public static boolean validarSenha(String password){
        boolean retorno = false;

        String PASSWORD_PATTERN_STRING = "^(?=.*[0-9])" +       // 1 Número
                                         "(?=.*[A-Z])"  +       // 1 Letra Maiúscula
                                         "(?=.*[@#$%^&+=])"  +  // 1 Caractere Especial
                                         "(?=\\S+$).{3,}$";     // Pelo menos 3 caracteres

        Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_PATTERN_STRING);

        if (password.isEmpty()) {
            retorno = false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            retorno = false;
        } else {
            retorno = true;
        }

        return  retorno;
    }

    public static boolean validarEmail(String email) {
        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }

        return false;
    }

    public static boolean validarCPF(String cpf) {
        if (cpf.equals("00000000000") ||
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999") ||
                (cpf.length() != 11)) {

            return false;
        }

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
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException ex) {
            return false;
        }
    }

    public static String formatarNumeroConta(String numeroConta) {
        String numeroContaFormatada = "";

        if (numeroConta.length() == 9) {
            char[] chars = numeroConta.toCharArray();

            for (int i = 0; i < chars.length ; i++) {
                numeroContaFormatada = numeroContaFormatada + chars[i];
                if(i == 1) {
                    numeroContaFormatada = numeroContaFormatada + ".";
                }

                if(i == 7) {
                    numeroContaFormatada = numeroContaFormatada + "-";
                }
            }
        } else {
            numeroContaFormatada = numeroConta;
        }

        return numeroContaFormatada;
    }

    public static boolean VerificaConexao(Context contexto){
        boolean lblnRet = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()){
                lblnRet = true;
            }else{
                lblnRet = false;
            }
        }catch (Exception e) {
        }
        return lblnRet;
    }

}
