package com.riso.zup.bank.helpers;

import android.text.TextUtils;

import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean userValidator(String user) {

        if(TextUtils.isEmpty(user)){
            return false;
        }

        if(emailPatternValidator.EMAIL_ADDRESS.matcher(user).matches()){
            return true;
        }
        else

        if(cpfValidator(user)){
            return true;
        }

        return false;
    }

    public static boolean passwordValidator(String password) {

        Pattern pattern = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        boolean hasSpecialCharacter = matcher.find();

        pattern = Pattern.compile("[0-9 ]", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(password);
        boolean hasNumber = matcher.find();

        if(!password.equals(password.toLowerCase()) && hasSpecialCharacter && hasNumber){
            return true;
        }

        return false;
    }

    public static boolean cpfValidator(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sum, i, r, num, weight;

        try {
            sum = 0;
            weight = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sum = sum + (num * weight);
                weight = weight - 1;
            }

            r = 11 - (sum % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sum = 0;
            weight = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sum = sum + (num * weight);
                weight = weight - 1;
            }

            r = 11 - (sum % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException err) {
            return(false);
        }
    }

    protected static class emailPatternValidator{
        private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+" +
                "(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        private static final Pattern EMAIL_ADDRESS=Pattern.compile(PATTERN_EMAIL);
    }
}
