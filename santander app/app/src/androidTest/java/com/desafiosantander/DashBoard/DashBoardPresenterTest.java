package com.desafiosantander.DashBoard;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DashBoardPresenterTest {
    DashBoardPresenter SUT;

    @Before
    public void setUp() throws Exception {
        SUT = new DashBoardPresenter();
    }


    @Test
    public void setStatmentList() {
        SUT = mock(DashBoardPresenter.class);
        List<Statement> mockList = mock(List.class);
        SUT.setStatmentList(mockList);

        Mockito.verify(SUT).setStatmentList(mockList);
    }
}