package com.example;

import com.example.appbank.ui.login.LoginContract;
import com.example.appbank.ui.login.LoginPresenter;
import com.example.appbank.ui.login.MainActivity;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class testLogin extends TestCase {

    @Mock
    private LoginContract.LoginView view;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testLoginSucessTrue() {
        LoginPresenter loginPresenter = new LoginPresenter(null);
        boolean b = loginPresenter.validPasswordData("A1@kl35");
        Assert.assertEquals(true, b);
    }


    @Test
    public void testLoginIsEmpty() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean a = loginPresenter.validPasswordData(" ");
        Assert.assertEquals(false, a);
    }

    @Test
    public void testLoginLowerCase() {
        LoginContract.LoginView view = new MainActivity();
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("ehi@2010");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testLoginUpperCase() {
        LoginContract.LoginView view = new MainActivity();
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("EHI@2010");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testLoginNoSpecialCharacter() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("ehi2010");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testLoginNoNumbers() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("Qhahan@");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testLoginEspace() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("Qhahan @");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testLoginNoLetter() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("123@123");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testLoginLessThan4Characters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("12a");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testLoginTowSpecialCharacters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("Ehi@@2010");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailTrue() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michelle_araujo92@hotmail.com");
        Assert.assertEquals(true, b);
    }

    @Test
    public void testEmailWithoutSpecialCharacters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michellearaujo92hotmailcom");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithSpace() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michelle.araujo92@hotmail. com");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithoutDomain() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michelle.araujo@");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithoutDotCom() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michelle.araujo@gmail");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailUpperCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("MICHELLE.ARAUJO@GMAIL.COM");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailIsEmpty() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData(" ");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailTowSpecialCharacters() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michelle.araujo@@gmail.com");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailOnlyOneUpperCase() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michelle.araujo@Gmail.com");
        Assert.assertEquals(false, b);
    }

    @Test
    public void testEmailWithNunbers() {
        LoginPresenter loginPresenter = new LoginPresenter(view);
        boolean b = loginPresenter.validPasswordData("michelle.araujo56@gmail.com");
        Assert.assertEquals(true, b);
    }

}
