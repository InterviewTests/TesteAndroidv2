package com.desafiosantander.Home;

import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

public class HomeInteractorTest {
    HomeInteractor SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new HomeInteractor();
    }


    @Test
    public void doLogin() {
        SUT = mock(HomeInteractor.class);
        SUT.doLogin("Gui@123", "Password@123");

        Mockito.verify(SUT).doLogin("Gui@123", "Password@123");
    }

    @Test
    public void getResponse() {

        SUT = mock(HomeInteractor.class);
        SUT.getResponse(true, "");

        Mockito.verify(SUT).getResponse(true, "");
    }
}