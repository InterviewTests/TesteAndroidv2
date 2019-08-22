package br.com.giovanni.testebank.Model;

import java.util.regex.Pattern;

public class PasswordControl {

    private String getPassword;

    public PasswordControl(String getPassword) {
        this.getPassword = getPassword;
    }

    public static final Pattern PATTERN_1 = Pattern.compile("[0-9]{1,}");
    public static final Pattern PATTERN_2 = Pattern.compile("[a-z]{1,}");
    public static final Pattern PATTERN_3 = Pattern.compile("[A-Z]{1,}");
    public static final Pattern PATTERN_4 = Pattern.compile("[@#$%^&+=_]{1,}");

    public boolean PATTERN_FULL() {
        return PATTERN_1.matcher(getPassword).find() &&
                PATTERN_2.matcher(getPassword).find() &&
                PATTERN_3.matcher(getPassword).find() &&
                PATTERN_4.matcher(getPassword).find();
    }

}