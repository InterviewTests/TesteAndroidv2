package com.desafiosantander.Home;

import com.desafiosantander.LoggedUser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class HomeRouterTest {
    HomeRouter SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new HomeRouter();
    }

    @Test
    public void login() {
        SUT = mock(HomeRouter.class);
        LoggedUser user = new LoggedUser("20", "Gui", "345345", "1010", "0,00");
        SUT.login(user);

        Mockito.verify(SUT).login(user);
    }
}