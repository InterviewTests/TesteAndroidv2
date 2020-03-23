package com.bankapp.statementScreen;


import android.content.Context;
import android.os.Build;

import com.bankapp.ErrorResponse;
import com.bankapp.api.RequestListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.KITKAT)
public class StatementInteractorUnitTest {

    StatementInteractor statementInteractor;
    StatementPresenterOutputSpy statementPresenterOutputSpy;
    StatementWorkerOutputSpy statementWorkerOutputSpy;

    @Before
    public void setUp(){
        statementPresenterOutputSpy = new StatementPresenterOutputSpy();
        statementWorkerOutputSpy = new StatementWorkerOutputSpy();
        statementInteractor = new StatementInteractor();
        statementInteractor.output = statementPresenterOutputSpy;
        statementInteractor.aStatementWorkerInput = statementWorkerOutputSpy;
    }

    @Test
    public void getLoginWorkerInput_shouldCall_LoginWorkerInput_notNull(){
        assertNotNull(statementInteractor.getStatementWorkerInput());
    }

    @Test
    public void logout_shouldCall_removeUserFromSharedPreferences(){
        statementInteractor.logout(null);
        assertTrue(statementWorkerOutputSpy.removeUserFromSharedPreferencesIsCalled);
    }

    @Test
    public void getStatements_sucess_shouldCall_presentStatementData(){
        statementWorkerOutputSpy.getStatementsSucess = true;
        statementInteractor.getStatements(null);
        assertTrue(statementPresenterOutputSpy.presentStatementDataIsCalled);
    }

    @Test
    public void getStatements_sucess_with_error_shouldCall_presentErrorStatementData(){
        statementWorkerOutputSpy.getStatementsSucessWithError = true;
        statementInteractor.getStatements(null);
        assertTrue(statementPresenterOutputSpy.presentErrorStatementDataIsCalled);
    }

    @Test
    public void getStatements_failure_shouldCall_presentStatementData(){
        statementWorkerOutputSpy.getStatementsFailure = true;
        statementInteractor.getStatements(null);
        assertTrue(statementPresenterOutputSpy.presentErrorStatementDataIsCalled);
    }




    private class StatementPresenterOutputSpy implements StatementPresenterInput {

        boolean presentStatementDataIsCalled = false;
        boolean presentErrorStatementDataIsCalled = false;

        @Override
        public void presentStatementData(StatementResponse response) {
            presentStatementDataIsCalled = true;
        }

        @Override
        public void presentErrorStatementData(ErrorResponse error) {
            presentErrorStatementDataIsCalled = true;
        }
    }

    private class StatementWorkerOutputSpy implements StatementWorkerInput {

        boolean getStatementsIsCalled = false;
        boolean removeUserFromSharedPreferencesIsCalled = false;
        boolean getStatementsSucess = false;
        boolean getStatementsSucessWithError = false;
        boolean getStatementsFailure = false;

        @Override
        public void getStatements(StatementRequest statementRequest, RequestListener<StatementResponse> responseListener) {
            getStatementsIsCalled = true;
            if(getStatementsSucess) {
                responseListener.onSuccess(new StatementResponse());
            }
            if(getStatementsSucessWithError){
                responseListener.onFailure(new StatementResponse());
            }
            if(getStatementsFailure) {
                responseListener.onFailure(new ErrorResponse("Error"));
            }
        }

        @Override
        public void removeUserFromSharedPreferences(Context context) {
            removeUserFromSharedPreferencesIsCalled = true;
        }
    }
}
