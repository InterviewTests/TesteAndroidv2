package com.testeandroidv2.loginScreen;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;

public class LoginPresentUnitTest {

    @Test
    public void presentLoginData_with_vaildInput_shouldCall_displayLoginData(){
        // Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        LoginRequest loginRequest = new LoginRequest();
        LoginWorker loginWorker = new LoginWorker();
        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();

        // Setup Test Double
        loginWorker.buildRequest(loginRequest);
        loginWorker.getLoginResponse(null);
        loginResponse.userAccount = new LoginModel(1, "Teste", 123, "45678", 3.33);
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        // When
        loginPresenter.presentLoginData(loginResponse);

        // Then
        Assert.assertTrue("When the valid input is passed to LoginPresenter Then displayLoginData should be called", loginActivityInputSpy.isDisplayLoginDataIsCalled);
    }
}

class LoginActivityInputSpy implements LoginActivityInput{

    boolean isDisplayLoginDataIsCalled = false;
    boolean isDisplayLoginErroIsCalled = false;

    @Override
    public void displayLoginData(LoginViewModel viewModel) {
        isDisplayLoginDataIsCalled = true;
    }

    @Override
    public void displayLoginErro(Object erro) {
        boolean isDisplayLoginErroIsCalled = true;
    }
}
