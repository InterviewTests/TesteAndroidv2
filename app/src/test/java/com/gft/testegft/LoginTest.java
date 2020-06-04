package com.gft.testegft;

import com.gft.testegft.login.LoginValidation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private LoginValidation loginValidation = new LoginValidation();
    private String password;

    @Test
    public void password_isEmpty() {
        password = "";
        assertFalse(loginValidation.passwordValidation(password));
    }

    @Test
    public void password_isInvalid_noNumbers() {
        password = "#Pass";
        assertFalse(loginValidation.passwordValidation(password));
    }

    @Test
    public void password_isInvalid_noSpecialCharacters() {
        password = "Pass1";
        assertFalse(loginValidation.passwordValidation(password));
    }

    @Test
    public void password_isInvalid_noUppercase() {
        password = "#pass1";
        assertFalse(loginValidation.passwordValidation(password));
    }

    @Test
    public void password_isValid() {
        password = "#Pass1";
        assertTrue(loginValidation.passwordValidation(password));
    }

}
