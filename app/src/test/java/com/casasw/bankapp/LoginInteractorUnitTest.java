package com.casasw.bankapp;

import android.content.Context;

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
        //LoginRequest loginRequest =  new LoginRequest(getValidLogin(), this);


        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        //loginInteractor.fetchLoginData(loginRequest);

        Assert.assertTrue(loginWorkerInputSpy.isgetLoginDataMethodCalled);
    }

    @Test
    public void fetchLoginMetaData_with_validInput_shouldCall_presentLoginData(){

        LoginInteractor loginInteractor = new LoginInteractor();
        //LoginRequest loginRequest = new LoginRequest(getValidLogin());

        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;

        //loginInteractor.fetchLoginData(loginRequest);

        Assert.assertTrue(loginPresenterInputSpy.presentLoginDataIsCalled);
    }

    private LoginModel getValidLogin() {
        return new LoginModel("test_user", "Test@1");
    }

    public static class LoginWorkerInputSpy implements LoginWorkerInput {

        boolean isgetLoginDataMethodCalled = false;

        @Override
        public String getLoginData(LoginModel login, Context context) {
            isgetLoginDataMethodCalled = true;
            return getValidLoginData();
        }
        private String getValidLoginData() {
            return "{\n" +
                    "    \"userAccount\": {\n" +
                    "        \"userId\": 1,\n" +
                    "        \"name\": \"Jose da Silva Teste\",\n" +
                    "        \"bankAccount\": \"2050\",\n" +
                    "        \"agency\": \"012314564\",\n" +
                    "        \"balance\": 3.3445\n" +
                    "    },\n" +
                    "    \"error\": {}\n" +
                    "}";
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
