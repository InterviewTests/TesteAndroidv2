package com.testeandroidv2.utility;

public class Validation {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String NUMBER_PATTERN = "[-+]?\\d*\\.?\\d+";
    private static final String CPF_PATTERN = "^([0-9]{3}\\.?){3}-?[0-9]{2}$";

    public static boolean isValidCPF(String cpf) {
        return cpf.matches(CPF_PATTERN);
    }

    public static boolean isValidEmail(String email){
        return email.matches(EMAIL_PATTERN);
    }

    public static boolean isNumeric(String s) {
        return s != null && s.matches(NUMBER_PATTERN);
    }
}
