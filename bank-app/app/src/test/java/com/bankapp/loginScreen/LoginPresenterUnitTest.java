package com.bankapp.loginScreen;

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
public class LoginPresenterUnitTest {

    LoginPresenter loginPresenter;
    LoginResponse loginResponse;
    LoginActivityInputSpy loginActivityInputSpy;

    @Before
    public void setUp(){
        loginPresenter = new LoginPresenter();
        loginResponse = new LoginResponse();
        loginActivityInputSpy = new LoginActivityInputSpy();
        loginPresenter.output = new WeakReference<>(loginActivityInputSpy);
    }

    @Test
    public void responseLogin_shouldCall_signIn() {
        loginPresenter.responseLogin(loginResponse);

        assertTrue(loginActivityInputSpy.signInIsCalled);
    }

    @Test
    public void responseErrorLogin_shouldCall_errorSignIn() {
        loginPresenter.responseErrorLogin(new ErrorResponse(""));

        assertTrue(loginActivityInputSpy.errorSignInIsCalled);
    }

    @Test
    public void responseErrorInvalidFields_shouldCall_errorUserOrPasswordInvalid_with_data() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.wrongPassword = true;
        loginResponse.wrongUser = true;
        loginPresenter.responseErrorInvalidFields(loginResponse);

        assertTrue(loginActivityInputSpy.errorUserOrPasswordInvalidIsCalled
                && loginActivityInputSpy.loginViewModelCopy.wrongPassword==loginResponse.wrongPassword
                && loginActivityInputSpy.loginViewModelCopy.wrongUser==loginResponse.wrongUser);
    }

    @Test
    public void responseSavedUser_shouldCall_bindLoginFields_with_data() {
        String testUser = "User test";
        String testPass = "PassTest";

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.loginModel = new LoginModel(testUser, testPass);
        loginPresenter.responseSavedUser(loginResponse);

        assertTrue(loginActivityInputSpy.bindLoginFieldsIsCalled
                && loginActivityInputSpy.loginViewModelCopy.loginModel.user==loginResponse.loginModel.user
                && loginActivityInputSpy.loginViewModelCopy.loginModel.password==loginResponse.loginModel.password);
    }

    @Test
    public void responseSavedUser_with_null_fields_shouldCall_bindLoginFields_with_data() {
        String testUser = "";
        String testPass = "";

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.loginModel = new LoginModel(null, null);
        loginPresenter.responseSavedUser(loginResponse);

        assertTrue(loginActivityInputSpy.bindLoginFieldsIsCalled
                && loginActivityInputSpy.loginViewModelCopy.loginModel.user==testUser
                && loginActivityInputSpy.loginViewModelCopy.loginModel.password==testPass);
    }



    private class LoginActivityInputSpy implements LoginActivityInput {

        boolean signInIsCalled = false;
        boolean errorSignInIsCalled = false;
        boolean bindLoginFieldsIsCalled = false;
        boolean errorUserOrPasswordInvalidIsCalled = false;

        LoginViewModel loginViewModelCopy;

        @Override
        public void signIn(LoginViewModel viewModel) {
            signInIsCalled = true;

        }

        @Override
        public void errorSignIn(LoginViewModel viewModel) {
            errorSignInIsCalled = true;
        }

        @Override
        public void bindLoginFields(LoginViewModel loginViewModel) {
            bindLoginFieldsIsCalled = true;
            loginViewModelCopy = loginViewModel;

        }

        @Override
        public void errorUserOrPasswordInvalid(LoginViewModel loginViewModel) {
            errorUserOrPasswordInvalidIsCalled = true;
            loginViewModelCopy = new LoginViewModel();
            loginViewModelCopy.wrongUser = loginViewModel.wrongPassword;
            loginViewModelCopy.wrongPassword = loginViewModel.wrongUser;
        }
    }

}
