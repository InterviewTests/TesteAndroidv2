package com.bank.testeandroidv2.loginScreen;

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
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void presentLoginDataValidationForm_with_validInput_shouldCall_displayLoginData() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.presentLoginDataValidationForm(loginResponse);

        //Then
        Assert.assertTrue("When the valid input is passed to LoginPresenter Then displayLoginMetaData should be called",
                loginActivityInputSpy.isProcessLoginDataFormCalled);
    }


    @Test
    public void processRequestLoginDataOnServer_with_success_shouldCall_callNextScene() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        UserAccount uc = new UserAccount();
        uc.userId = 1L;
        uc.name = "Jose da Silva Teste";
        uc.agency = "012314564";
        uc.bankAccount = "2050";
        uc.balance = 3.3445;

        //When
        loginPresenter.processRequestLoginDataOnServer(uc);

        //Then
        Assert.assertTrue(loginActivityInputSpy.isCallNextSceneCalled);
        Assert.assertNotNull(loginActivityInputSpy.userAccountCopy);
    }

    @Test
    public void processErrorRequest_with_error_shouldCall_callApiError() {
        //Given
        LoginPresenter loginPresenter = new LoginPresenter();
        LoginResponse loginResponse = new LoginResponse();

        LoginActivityInputSpy loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<LoginActivityInput>(loginActivityInputSpy);

        //When
        loginPresenter.processErrorRequest(null);

        //Then
        Assert.assertTrue(loginActivityInputSpy.isCallApiErrorCalled);
    }


    private class LoginActivityInputSpy implements LoginActivityInput {
        public boolean isProcessLoginDataFormCalled = false;
        public boolean isCallNextSceneCalled = false;
        public boolean isCallApiErrorCalled = false;
        public LoginViewModel loginViewModelCopy;
        public UserAccount userAccountCopy;
        public String errorCopy;

        @Override
        public void processLoginDataForm(LoginViewModel loginViewModel) {
            isProcessLoginDataFormCalled = true;
            loginViewModelCopy = loginViewModel;
        }

        @Override
        public void callNextScene(UserAccount response) {
            isCallNextSceneCalled = true;
            userAccountCopy = response;
        }

        @Override
        public void callApiError(String error) {
            isCallApiErrorCalled = true;
            errorCopy = error;
        }
    }
}
