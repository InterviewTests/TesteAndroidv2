package com.desafiosantander.Home;

import android.content.Context;

import com.desafiosantander.LoggedUser;

import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.lang.ref.WeakReference;

import static org.junit.Assert.*;

public class HomePresenterTest {
    HomePresenter SUT;


    @Before
    public void setUp() throws Exception {
        SUT = new HomePresenter();

    }

    @Test
    public void validadeUser_methodInvocation() {
        //Arrage
        //Act
        SUT = mock(HomePresenter.class);
        SUT.validadeUser("Gui@123");
        //Assert
        Mockito.verify(SUT).validadeUser(anyString());
    }

    @Test
    public void validadePassword_methodInvocation() {
        //Arrage
        //Act
        SUT = mock(HomePresenter.class);
        SUT.validadePassword("DFJDOifd123#");
        //Assert
        Mockito.verify(SUT).validadePassword(anyString());
    }

    @Test
    public void login_methodInvocation() {
        //Arrage
        //Act
        SUT = mock(HomePresenter.class);
        LoggedUser user = new LoggedUser("20", "Gui", "345345", "1010", "0,00");
        SUT.login(true, user);
        //Assert
        Mockito.verify(SUT).login(true, user);
    }

    @Test
    public void isCPF_validCpf_trueReturned() {
        boolean result = SUT.isCPF("50158571010");
        //Assert.assertTrue(result);
        Assert.assertThat(result, CoreMatchers.is(true));

    }

    @Test
    public void isCPF_invalidCpf_falseReturned() {
        boolean result = SUT.isCPF("50");
        Assert.assertFalse(result);

    }

    @Test
    public void checkCapitalLetter_invalidString_falseReturned() {
        boolean result = SUT.checkCapitalLetter("");
        Assert.assertFalse(result);
    }

    @Test
    public void checkCapitalLetter_validString_TrueReturned() {
        boolean result = SUT.checkCapitalLetter("Gui");
        Assert.assertTrue(result);
    }

    @Test
    public void checkSpecial_validString_TrueReturned() {
        boolean result = SUT.checkSpecial("Gui@123");
        Assert.assertTrue(result);
    }
    @Test
    public void checkSpecial_invalidString_falseReturned() {
        boolean result = SUT.checkSpecial("Gui123");
        Assert.assertFalse(result);
    }

    @Test
    public void checkNumber_validString_TrueReturned() {
        boolean result = SUT.checkNumber("Gui@123");
        Assert.assertTrue(result);
    }

    @Test
    public void checkNumber_invalidString_falseReturned() {
        boolean result = SUT.checkNumber("!@#$%");
        Assert.assertFalse(result);
    }
}