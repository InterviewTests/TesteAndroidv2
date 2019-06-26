package com.everis.TesteAndroidv2.Base.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static junit.framework.TestCase.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class ValidatorsTest {

    @Test
    public void isValidCPF() {
        assertTrue(!Validators.isValidCPF(""));
        assertTrue(!Validators.isValidCPF("123"));
        assertTrue(!Validators.isValidCPF("abcdefghijkl"));
        assertTrue(!Validators.isValidCPF("a@a.a"));
        assertTrue(!Validators.isValidCPF("1@1.1"));
        assertTrue(!Validators.isValidCPF("12345678901"));
        assertTrue(!Validators.isValidCPF("97331751883a"));
        assertTrue(Validators.isValidCPF("97331751883"));
    }

    @Test
    public void isEmail(){
        assertTrue(!Validators.isEmail(""));
        assertTrue(!Validators.isEmail("12345678901"));
        assertTrue(!Validators.isEmail("97331751883"));
        assertTrue(Validators.isEmail("a@a.a"));
        assertTrue(Validators.isEmail("1@1.1"));
    }

    @Test
    public void isValidPassword() {
        assertTrue(!Validators.isValidPassword("123"));
        assertTrue(!Validators.isValidPassword("abc"));
        assertTrue(!Validators.isValidPassword("ABC"));
        assertTrue(!Validators.isValidPassword("!@#"));
        assertTrue(!Validators.isValidPassword("123abcABC"));
        assertTrue(!Validators.isValidPassword("abcABC!@#"));
        assertTrue(Validators.isValidPassword("1A@"));
    }
}