package com.example.testesantander;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.testesantander.login.LoginContract;
import com.example.testesantander.login.LoginPresenter;

public class TesteLogin extends TestCase {
    @Mock
    private LoginContract.View view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    //teste password
    @Test
    public void testPasswordSuccess() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("A@a123");
        Assert.assertEquals(true, b);
    }
    @Test
    public void testPasswordNull() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordLowerCaseWithoutUpperCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("a@123");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordUpperCaseWithoutLowerCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("AA@123");
        Assert.assertEquals(true, b);
    }
    @Test
    public void testPasswordWithBlanckSpace() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("A@a 123");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordWith3Characeters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("A@1");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordWithoutNumbers() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("A@aa!#");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordWithoutLetters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("!@#123");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordWithoutSpecialCharacters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("Aa123");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordOnlyNumbers() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("567123");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordOnlyLetters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("AasbB");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordOnlyCharactersSpecials() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("@!#$%!!");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordOnlyUpperCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("AAABCD");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordOnlyLowerCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("aa121!@@");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testPasswordWith2CharactersSpecials() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPassword("A@!a123");
        Assert.assertEquals(true, b);
    }

    //teste email
    @Test
    public void testEmailSuccess() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("t@t.com");
        Assert.assertEquals(true, b);
    }
    @Test
    public void testEmailNull() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername(" ");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testEmailLowerCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("t@t.com");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailUpperCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("TTT@TTT.COM");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithBlanckSpace() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("asa @klol.com");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWith3Characters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qwe@qwe.com");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithoutNumber() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qwe@qwe.com");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithoutLetters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("12345@1234.1234");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithoutSpecialCharacters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qweqwecom");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithOnlyNumbers() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("123@123.123");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithCharactersSpecial() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("!#$@!#$.!#$");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWith2CharacterSpecial() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qwe@@qwe.com");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithoutDomain() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qwe@qwe");
        Assert.assertEquals(false, b);

    }
    @Test
    public void testEmailWithoutCharacterSpecial() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qweqwe.com");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithDot() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qwe@qwecom");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithDotComDotBr() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("qwe@qwe.com.br");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithOnlyLetter() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("q@q.q");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithOnlyLetterUpperCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("L@L.L");
        Assert.assertEquals(true, b);
    }

    //testes CPF
    @Test
    public void testCPFSuccess() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validUsername("12345678910");
        Assert.assertEquals(true, b);
    }
    @Test
    public void testCPFMinusNumbers(){
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b =loginPresenter.validUsername("123456789");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testCPFMoreNumbers(){
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b =loginPresenter.validUsername("123456789011");
        Assert.assertEquals(false, b);
    }
    @Test
    public void testCPFWith3Characters(){
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b =loginPresenter.validUsername("123");
        Assert.assertEquals(false, b);
    }











}
