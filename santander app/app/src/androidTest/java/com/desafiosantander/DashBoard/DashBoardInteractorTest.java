package com.desafiosantander.DashBoard;

import com.desafiosantander.Home.HomeInteractor;
import com.desafiosantander.Home.HomeRouter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DashBoardInteractorTest {
    DashBoardInteractor SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new DashBoardInteractor();
    }


    @Test
    public void loadStatements() {
        SUT = mock(DashBoardInteractor.class);
        SUT.loadStatements();

        Mockito.verify(SUT).loadStatements();
    }

    @Test
    public void getResponse() {

        SUT = mock(DashBoardInteractor.class);
        SUT.getResponse(true, "response");

        Mockito.verify(SUT).getResponse(true, "response");
    }
}