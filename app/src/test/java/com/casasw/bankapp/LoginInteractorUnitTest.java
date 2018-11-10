package com.casasw.bankapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoginInteractorUnitTest {

    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void fetchLoginData_with_validInput_shouldCall_Worker_getLoginData(){
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest =  new LoginRequest();
        loginRequest.isLogOn = true;

        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        loginInteractor.fetchLoginData(loginRequest);

        Assert.assertTrue(loginWorkerInputSpy.isgetLoginDataMethodCalled);
    }

    @Test
    public void fetchLoginMetaData_with_validInput_shouldCall_presentLoginData(){

        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.isLogOn = true;
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;

        loginInteractor.fetchLoginData(loginRequest);

        Assert.assertTrue(loginPresenterInputSpy.presentLoginDataIsCalled);
    }

    public static class LoginWorkerInputSpy implements LoginWorkerInput {

        boolean isgetLoginDataMethodCalled = false;

        @Override
        public String getLoginData() {
            isgetLoginDataMethodCalled = true;
            return getLoginData();
        }
    }

    public static class LoginPresenterInputSpy implements LoginPresenterInput {
        boolean presentLoginDataIsCalled = false;
        LoginResponse loginResponseCopy;
        @Override
        public void presentLoginData(LoginResponse response) {
            presentLoginDataIsCalled = true;
            loginResponseCopy = response;
        }
    }
}
