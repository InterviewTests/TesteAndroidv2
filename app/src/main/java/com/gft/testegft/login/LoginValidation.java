package com.gft.testegft.login;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class LoginValidation {

    @Inject
    public LoginValidation() {

    }

    static public boolean passwordValidation(String password) {
        Pattern PASSWORD_PATTERN =
                Pattern.compile("^" +
                        "(?=.*[0-9])" +         // at least 1 digit
                        "(?=.*[A-Z])" +         // at least 1 uppercase letter
                        "(?=.*[@#$%^&+=])" +    // at least 1 special character
                        ".*" +                  // any other thing
                        "$");

        Matcher matcher = PASSWORD_PATTERN.matcher(password);

        return matcher.matches();
    }
}
