package com.example.rossinyamaral.bank.loginScreen;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginInteractorUnitTest {

    @Test
    public void fetchLoginMetaData_with_vaildInput_shouldCall_presentLoginMetaData() {
        //Given
        LoginInteractorInputSpy loginInteractor = new LoginInteractorInputSpy();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "test";
        loginRequest.password = "test";
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.output = loginPresenterInputSpy;
        //When
        loginInteractor.fetchLoginData(loginRequest);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginInteractor " +
                        "Then presentLoginMetaData should be called",
                loginInteractor.interactorLoginDataIsCalled);
    }

    @Test
    public void fetchLoginData_with_vaildInput_shouldCall_presentLoginData() {
        //Given
        LoginInteractorInputSpy loginInteractor = new LoginInteractorInputSpy();
        LoginRequest loginRequest = new LoginRequest();

        //Setup TestDoubles
        LoginPresenterInputSpy output = new LoginPresenterInputSpy();
        loginInteractor.output = output;

        //When
        loginInteractor.fetchLoginData(loginRequest);

        //Then
        Assert.assertTrue("When the request is passed to LoginInteractor" +
                        "Then presentLoginData should be called",
                loginInteractor.interactorLoginDataIsCalled);
    }

    private class LoginPresenterInputSpy implements LoginPresenterInput {

        boolean presentLoginDataIsCalled = false;
        LoginResponse loginResponseCopy;

        @Override
        public void presentLoginData(LoginResponse response) {
            presentLoginDataIsCalled = true;
            loginResponseCopy = response;
        }
    }

    private class LoginInteractorInputSpy implements LoginInteractorInput {

        boolean interactorLoginDataIsCalled = false;
        LoginRequest loginRequestCopy;
        LoginPresenterInput output;

        @Override
        public void fetchLoginData(LoginRequest request) {
            interactorLoginDataIsCalled = true;
            loginRequestCopy = request;
        }
    }
}