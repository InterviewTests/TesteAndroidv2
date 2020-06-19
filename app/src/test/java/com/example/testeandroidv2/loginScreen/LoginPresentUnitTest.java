package com.example.testeandroidv2.loginScreen;

import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.WeakReference;

public class LoginPresentUnitTest {

    @Test
    public void presentLoginData_with_userAccountNot_empty_shouldCall_displayLoginData(){
        // Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        LoginRequest loginRequest = new LoginRequest();
        LoginWorker loginWorker = new LoginWorker();
        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();

        // Setup Test Double
        loginWorker.buildRequest(loginRequest);
        loginWorker.getLoginResponse(null);
        loginResponse.userAccount = new UserModel(1, "Teste", "123", "45678", 3.33);
        loginPresenter.output = new WeakReference<>(loginActivityInputSpy);

        // When
        loginPresenter.presentLoginData(loginResponse);

        // Then
        Assert.assertTrue(loginActivityInputSpy.isDisplayLoginDataIsCalled);
    }

    @Test
    public void presentLoginData_with_userAccount_empty_shouldCall_displayLoginError(){
        // Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        LoginRequest loginRequest = new LoginRequest();
        LoginWorker loginWorker = new LoginWorker();
        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();

        // Setup Test Double
        loginWorker.buildRequest(loginRequest);
        loginWorker.getLoginResponse(null);
        loginPresenter.output = new WeakReference<>(loginActivityInputSpy);

        // When
        loginPresenter.presentLoginData(loginResponse);

        // Then
        Assert.assertTrue(loginActivityInputSpy.isDisplayLoginErrorIsCalled);
    }
}

class LoginActivityInputSpy implements LoginActivityInput{

    boolean isDisplayLoginDataIsCalled = false;
    boolean isDisplayLoginErrorIsCalled = false;

    @Override
    public void displayLoginData(UserViewModel userViewModel) {
        isDisplayLoginDataIsCalled = true;
    }

    @Override
    public void displayLoginErro(Object erro) {
        isDisplayLoginErrorIsCalled = true;
    }
}
