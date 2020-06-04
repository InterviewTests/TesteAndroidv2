package com.gft.testegft;

import com.gft.testegft.login.utils.LoginValidation;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginTest {

    private String user;
    private String password;

    @Test
    public void user_email_isEmpty() {
        user = "";
        assertFalse(LoginValidation.userValidation(user));
    }

    @Test
    public void user_email_noAtSign() {
        user = "test";
        assertFalse(LoginValidation.userValidation(user));
    }

    @Test
    public void user_email_noDomain() {
        user = "test@";
        assertFalse(LoginValidation.userValidation(user));
    }

    @Test
    public void user_email_noDot() {
        user = "test@email";
        assertFalse(LoginValidation.userValidation(user));
    }

    @Test
    public void user_email_noEnd() {
        user = "test@email.";
        assertFalse(LoginValidation.userValidation(user));
    }

    @Test
    public void user_email_isValid() {
        user = "test@email.com";
        assertTrue(LoginValidation.userValidation(user));
    }

    @Test
    public void user_cpf_isBigger() {
        user = "123456789123456789";
        assertFalse(LoginValidation.userValidation(user));
    }

    @Test
    public void user_cpf_isSmaller() {
        user = "12345678";
        assertFalse(LoginValidation.userValidation(user));
    }

    @Test
    public void user_cpf_isValid() {
        user = "12345678911";
        assertTrue(LoginValidation.userValidation(user));
    }

    @Test
    public void user_cpf_dotted_isValid() {
        user = "123.456.789-11";
        assertTrue(LoginValidation.userValidation(user));
    }

    @Test
    public void password_isEmpty() {
        password = "";
        assertFalse(LoginValidation.passwordValidation(password));
    }

    @Test
    public void password_isInvalid_noNumbers() {
        password = "#Pass";
        assertFalse(LoginValidation.passwordValidation(password));
    }

    @Test
    public void password_isInvalid_noSpecialCharacters() {
        password = "Pass1";
        assertFalse(LoginValidation.passwordValidation(password));
    }

    @Test
    public void password_isInvalid_noUppercase() {
        password = "#pass1";
        assertFalse(LoginValidation.passwordValidation(password));
    }

    @Test
    public void password_isValid() {
        password = "#Pass1";
        assertTrue(LoginValidation.passwordValidation(password));
    }

}
