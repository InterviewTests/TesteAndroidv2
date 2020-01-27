package com.msbm.bank;

import com.msbm.bank.loginScreen.LoginInteractor;
import com.msbm.bank.utils.StringUtil;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginInteractorTest {

    LoginInteractor loginInteractor;

    @Before
    public void setUp() throws Exception {
        loginInteractor = new LoginInteractor();
    }

    @Test
    public void validEmailShouldReturnTrueTest() {
        String validEmail = "testValidEmail@gmail.com";
        assertTrue(StringUtil.validateEmail(validEmail));
    }

    @Test
    public void invalidEmailShouldReturnFalseTest() {
        String invalidEmail = "test";
        assertFalse(StringUtil.validateEmail(invalidEmail));
    }

    @Test
    public void validPasswordShouldReturnTrue() {
        String validPassword = "Test@1";
        assertTrue(StringUtil.hasUpperCharacter(validPassword));
    }

    @Test
    public void invalidPasswordShouldReturnFalse() {
        String validPassword = "test@1";
        assertFalse(StringUtil.hasUpperCharacter(validPassword));
    }
}