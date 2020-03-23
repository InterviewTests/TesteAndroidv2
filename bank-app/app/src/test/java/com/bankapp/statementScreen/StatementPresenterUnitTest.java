package com.bankapp.statementScreen;


import android.os.Build;

import com.bankapp.ErrorResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.KITKAT)
public class StatementPresenterUnitTest {

    StatementPresenter statementPresenter;
    StatementResponse statementResponse;
    StatementActivityInputSpy statementActivityInputSpy;

    @Before
    public void setUp(){
        statementPresenter = new StatementPresenter();
        statementResponse = new StatementResponse();
        statementActivityInputSpy = new StatementActivityInputSpy();
        statementPresenter.output = new WeakReference<>(statementActivityInputSpy);
    }

    @Test
    public void presentStatementData_shouldCall_displayStatementData() {
        statementPresenter.presentStatementData(new StatementResponse());

        assertTrue(statementActivityInputSpy.displayStatementDataIsCalled);
    }

    @Test
    public void presentErrorStatementData_shouldCall_displayErrorStatementData() {
        statementPresenter.presentErrorStatementData(new ErrorResponse(""));

        assertTrue(statementActivityInputSpy.displayErrorStatementDataIsCalled);
    }

    private class StatementActivityInputSpy implements StatementActivityInput {

        boolean displayStatementDataIsCalled = false;
        boolean displayErrorStatementDataIsCalled = false;

        @Override
        public void displayStatementData(StatementViewModel viewModel) {
            displayStatementDataIsCalled = true;
        }

        @Override
        public void displayErrorStatementData(ErrorResponse error) {
            displayErrorStatementDataIsCalled = true;
        }
    }
}
