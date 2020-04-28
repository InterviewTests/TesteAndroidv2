package com.br.example.fakebank.presentations.utils;

import java.util.regex.Pattern;

public class RegexValidateUtil {
    public static String PASSWORD_PATTERN = "(?=.*[a-z0-9])(?=.*[A-Z])(?=.*\\W+).*\\$";
    public static String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static String CPF_PATTERN = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)";

    public Boolean isValidField(String stringPattern, String password){
        Pattern pattern = Pattern.compile(stringPattern);
        return pattern.matcher(password).matches();
    }
}
