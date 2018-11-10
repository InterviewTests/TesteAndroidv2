package com.casasw.bankapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
public class LoginPresenterUnitTest {
    public static String TAG = LoginPresenterUnitTest.class.getSimpleName();

    @Before
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void presentLoginMetaData_with_vaildInput_shouldCall_displayLoginData(){
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.loginJSON = new LoginWorker().getLoginData();

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.presentLoginData(loginResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginPresenter Then displayLoginMetaData should be called", loginActivityInputSpy.isdisplayLoginMetaDataCalled);
    }

    @Test
    public void verify_LoginPresenter(){
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();
        LoginModel  loginModel = new LoginModel();
        /*popular login*/

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output =  new WeakReference<LoginActivityInput>(loginActivityInputSpy);
        /*popular presenter*/

        /*então contruir string esperadas e testar assert*/

    }

    private class LoginActivityInputSpy implements LoginActivityInput {
        public boolean isdisplayLoginMetaDataCalled = false;
        public LoginViewModel loginViewModelCopy;
        @Override
        public void displayLoginData(LoginViewModel loginViewModel) {
            isdisplayLoginMetaDataCalled = true;
            loginViewModelCopy = loginViewModel;
        }
    }
}
