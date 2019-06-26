package com.example.santanderapp.santander;

import com.example.santanderapp.santander.util.Utils;

import org.junit.Test;

import java.text.ParseException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class UtilsTest {

    //Verifica se o E-mail é válido ou não
    @Test
    public void validateEmail() {
        boolean isValidatesEmail = Utils.isEmailValid("teste@teste.com.br");
        assertTrue(isValidatesEmail);

        isValidatesEmail = Utils.isEmailValid("teste");
        assertTrue(!isValidatesEmail);

    }

    //Verifica se o CPF é válido ou não
    @Test
    public void validateCPF() {
        boolean isValidatesCPF = Utils.isCpfValid("35935125803");
        assertTrue(isValidatesCPF);

        isValidatesCPF = Utils.isCpfValid("35935125802");
        assertTrue(!isValidatesCPF);

    }

    //Verifica se a senha é válido ou não
    @Test
    public void validatePassword() {
        boolean isValidatesPassword = Utils.isPasswordValid("Q1@");
        assertTrue(isValidatesPassword);

        isValidatesPassword = Utils.isPasswordValid("teste");
        assertTrue(!isValidatesPassword);

    }

    //Verifica formatação do tipo do valor que vem na API do header
    @Test
    public void validateValueHeader() {
        String isValidateValueHeader = Utils.formatRealHeader("45.567");
        assertEquals(isValidateValueHeader,"R$ 455,67");

    }

    //Verifica formatação do tipo do valor que vem na API do header
    @Test
    public void validateValueReal() {
        String isValidateValue = Utils.formatReal("456.7");
        assertEquals(isValidateValue,"R$ 456,70");

        isValidateValue = Utils.formatReal("456.77");
        assertEquals(isValidateValue,"R$ 456,77");
    }

    //Verifica formatação do tipo da conta que vem na API do header
    @Test
    public void formatAccountHeader() {
        String isFormatAccountHeader = Utils.formatAccount("123456");
        assertEquals(isFormatAccountHeader,"12.345-6");
    }

    //Verifica formatação do tipo do valor que vem na API do header
    @Test
    public void formatDate() throws ParseException {
        String isFormatDate = Utils.convertData("2019-05-12");
        assertEquals(isFormatDate,"12/05/2019");
    }

}