package com.example.santanderapp.santander;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class LoginTest {

    @Test
    public void validateDateLogin() {
        boolean isValidatesData = LoginActivity.validatesData("35935125803", "Q1@");
        assertTrue(isValidatesData);
    }
}