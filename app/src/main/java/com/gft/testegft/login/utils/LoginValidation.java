package com.gft.testegft.login.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginValidation {

    static public boolean userValidation(String user) {
        Pattern USER_EMAIL_PATTERN =
                Pattern.compile("^" +
                        "([A-Za-z0-9._%+-])" +
                        "+@" +
                        "[A-Za-z0-9._%+-]" +
                        "+\\.+" +
                        "(.*[A-Za-z])" +
                        "$");

        Pattern USER_CPF_PATTERN =
                Pattern.compile("^" +
                        "([0-9.])" +
                        "{11}" +
                        "$");

        Pattern USER_DOTTED_CPF_PATTERN =
                Pattern.compile("^" +
                        "([0-9]){3}+.+" +
                        "([0-9]){3}+.+" +
                        "([0-9]){3}+-+" +
                        "([0-9]){2}" +
                        "$");

        Matcher matcherEmail = USER_EMAIL_PATTERN.matcher(user);
        Matcher matcherCpf = USER_CPF_PATTERN.matcher(user);
        Matcher matcherDottedCpf = USER_DOTTED_CPF_PATTERN.matcher(user);

        return matcherEmail.matches() || matcherCpf.matches() || matcherDottedCpf.matches();
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
