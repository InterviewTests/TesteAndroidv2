package com.example.fabio.bankapp.classes;

import android.text.Editable;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ValidarCampos {

    public static boolean ValidarCampoVazio(View pView, String pMessage) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String strText = text.toString();
                if (!strText.equals("")) {
                    return true;
                }
            }
            edText.setError(pMessage);
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }
    public static boolean ValidarCPF(View pView) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String CPF = text.toString();
                CPF = Mascara.RemoverMascara(CPF);
                if (!CPF.equals("00000000000") & !CPF.equals("11111111111") & !CPF.equals("22222222222") & !CPF.equals("33333333333")
                        & !CPF.equals("44444444444") & !CPF.equals("55555555555")
                        & !CPF.equals("66666666666") & !CPF.equals("77777777777")
                        & !CPF.equals("88888888888") & !CPF.equals("99999999999")) {

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
                        else
                            dig10 = (char) (r + 48);
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
                        else
                            dig11 = (char) (r + 48);
                        if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                            return (true);
                        else
                            return (false);
                    } catch (Exception erro) {
                        edText.setError("CPF inválido");
                        edText.setFocusable(true);
                        edText.requestFocus();
                        return (false);
                    }
                }

            }
            edText.setError("CPF inválido");
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public final static boolean ValidarEmail(View pView) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String email = text.toString();
                if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    return true;
                }
            }
            edText.setError("Email inválido");
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }

    public final static boolean ValidarPassword(View pView) {
        if (pView instanceof EditText) {
            EditText edText = (EditText) pView;
            Editable text = edText.getText();
            if (text != null) {
                String password = text.toString();
                Pattern pe = Pattern.compile("[^a-zA-Z0-9]");
                Pattern pm = Pattern.compile("[A-Z]");
                Pattern pn = Pattern.compile("[0-9]");
                boolean temespecial = pe.matcher(password).find();
                boolean temmaiuscula = pm.matcher(password).find();;
                boolean temnumero = pn.matcher(password).find();;

                if (temespecial & temmaiuscula & temnumero) {
                    return true;
                }
            }
            edText.setError("Password inválido");
            edText.setFocusable(true);
            edText.requestFocus();
            return false;
        }
        return false;
    }
}