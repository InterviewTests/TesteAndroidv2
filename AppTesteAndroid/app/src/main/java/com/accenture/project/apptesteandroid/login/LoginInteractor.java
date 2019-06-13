package com.accenture.project.apptesteandroid.login;

import android.util.Log;

import com.accenture.project.apptesteandroid.model.LoginRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

interface ILoginInteractor {

    void login(String user, String password);

    boolean validUser(String user);

    boolean validPassword(String password);
}

public class LoginInteractor implements ILoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractor() {

        loginRepository = new LoginRepository();
    }

    @Override
    public void login(String user, String password) {

        if (validUser(user) && validPassword(password)) {

            //call api

            LoginRequest loginRequest = new LoginRequest(user, password);
            loginRepository.getLoginResponse(loginRequest);

            Log.d("LoginLog", "login: call api");

        } else {

            //return message to user

            Log.d("LoginLog", "login: return message to user");
        }


    }

    @Override
    public boolean validUser(String user) {

        if (user != null && !user.isEmpty()) {

            //checks if user is an valid email
            if (user.contains("@") && user.contains(".com") &&
                    !user.substring(0, user.indexOf("@")).isEmpty() &&
                    !user.substring(user.indexOf("@") + 1, user.indexOf(".com")).isEmpty()) {

                return true;

                //checks if user is an valid CPF
            } else if (user.length() == 11) {

                boolean isDigit = true;

                for (int x = 0; x < user.length(); x++) {


                    if (!Character.isDigit(user.charAt(x))) {
                        isDigit = false;
                    }
                }

                //checks if CPF only contain numbers and is not a sequence of equals digits
                if (isDigit &&
                        !user.equals("00000000000") && !user.equals("11111111111") &&
                        !user.equals("22222222222") && !user.equals("33333333333") &&
                        !user.equals("44444444444") && !user.equals("55555555555") &&
                        !user.equals("66666666666") && !user.equals("77777777777") &&
                        !user.equals("88888888888") && !user.equals("99999999999")) {

                    return true;

                }

            }


        }

        return false;
    }


    @Override
    public boolean validPassword(String password) {

        if (password != null && !password.isEmpty()) {

            boolean containsUpperCase = false;
            boolean containsSpecialChar = false;
            boolean containsAlphabeticChar = false;
            boolean containsNumericChar = false;

            Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
            Matcher matcher = pattern.matcher(password);
            boolean hasSpecialChar = matcher.find();

            //checks if password contain a special character
            if (hasSpecialChar) {

                containsSpecialChar = true;
            }

            for (int x = 0; x < password.length(); x++) {

                //checks if password contain a upper case character
                if (Character.isUpperCase(password.charAt(x))) {
                    containsUpperCase = true;
                    break;
                }
            }

            for (int x = 0; x < password.length(); x++) {

                //checks if password contain a digit (number)
                if (Character.isDigit(password.charAt(x))) {
                    containsNumericChar = true;
                    break;
                }
            }

            for (int x = 0; x < password.length(); x++) {

                //checks if password contain a alphabetic character
                if (Character.isAlphabetic(password.charAt(x)) && !Character.isUpperCase(password.charAt(x))) {
                    containsAlphabeticChar = true;
                    break;
                }
            }

            return containsUpperCase && containsSpecialChar && containsAlphabeticChar &&
                    containsNumericChar;
        }

        return false;
    }
}
