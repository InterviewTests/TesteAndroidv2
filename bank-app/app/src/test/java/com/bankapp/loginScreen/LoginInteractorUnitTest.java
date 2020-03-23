package com.bankapp.loginScreen;


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
public class LoginInteractorUnitTest {

    LoginInteractor loginInteractor;
    LoginRequest request;
    LoginPresenterOutputSpy loginPresenterOutputSpy;
    LoginWorkerOutputSpy loginWorkerOutputSpy;

    @Before
    public void setUp(){
        loginPresenterOutputSpy = new LoginPresenterOutputSpy();
        loginWorkerOutputSpy = new LoginWorkerOutputSpy();
        loginInteractor = new LoginInteractor();
        loginInteractor.output = loginPresenterOutputSpy;
        loginInteractor.aLoginWorkerInput = loginWorkerOutputSpy;
        request = new LoginRequest();
    }

    @Test
    public void getLoginWorkerInput_shouldCall_LoginWorkerInput_notNull(){
        assertNotNull(loginInteractor.getLoginWorkerInput());
    }

    @Test
    public void doLogin_with_ivalidInput_shouldCall_responseErrorInvalidFields(){
        request.loginModel = new LoginModel("InvalidUser", "InvalidPass");
        loginInteractor.doLogin(null, request);

        assertTrue(loginPresenterOutputSpy.responseErrorInvalidFieldsIsCalled);
    }

    @Test
    public void doLogin_with_validInput_shouldCall_login(){
        request.loginModel = new LoginModel("teste@teste.com.br", "Test@123");
        loginInteractor.doLogin(null, request);

        assertTrue(loginWorkerOutputSpy.loginIsCalled);
    }

    @Test
    public void doLogin_sucess_shouldCall_setUserFromSharedPreferences(){
        request.loginModel = new LoginModel("teste@teste.com.br", "Test@123");
        loginWorkerOutputSpy.loginSucess = true;
        loginInteractor.doLogin(null, request);

        assertTrue(loginWorkerOutputSpy.setUserFromSharedPreferencesIsCalled);
    }

    @Test
    public void doLogin_sucess_shouldCall_responseLogin(){
        request.loginModel = new LoginModel("teste@teste.com.br", "Test@123");
        loginWorkerOutputSpy.loginSucess = true;
        loginInteractor.doLogin(null, request);

        assertTrue(loginPresenterOutputSpy.responseLoginIsCalled);
    }

    @Test
    public void doLogin_failure_shouldCall_responseErrorLogin(){
        request.loginModel = new LoginModel("teste@teste.com.br", "Test@123");
        loginWorkerOutputSpy.loginFailure = true;
        loginInteractor.doLogin(null, request);

        assertTrue(loginPresenterOutputSpy.responseErrorLoginIsCalled);
    }

    @Test
    public void doLogin_sucess_with_error_shouldCall_responseErrorLogin(){
        request.loginModel = new LoginModel("teste@teste.com.br", "Test@123");
        loginWorkerOutputSpy.loginSucessWithError = true;
        loginInteractor.doLogin(null, request);

        assertTrue(loginPresenterOutputSpy.responseErrorLoginIsCalled);
    }

    @Test
    public void getSavedUser_shouldCall_getUserFromSharedPreferences(){
        loginInteractor.getSavedUser(null);

        assertTrue(loginWorkerOutputSpy.getUserFromSharedPreferencesIsCalled);
    }

    @Test
    public void getSavedUser_shouldCall_responseSavedUser(){
        loginInteractor.getSavedUser(null);

        assertTrue(loginPresenterOutputSpy.responseSavedUserIsCalled);
    }

    @Test
    public void checkUserFormat_with_valid_Fields(){
        String user = "teste@teste.com.br";
        LoginResponse loginResponse = new LoginResponse();
        loginInteractor.checkUserFormat(user, loginResponse);

        assertTrue(!loginResponse.wrongUser);
    }

    @Test
    public void checkUserFormat_with_inValid_Fields(){
        String user = "teste";
        LoginResponse loginResponse = new LoginResponse();
        loginInteractor.checkUserFormat(user, loginResponse);

        assertTrue(loginResponse.wrongUser);
    }

    @Test
    public void checkPasswordFormat_with_valid_Pass(){
        String pass = "Test@123";
        LoginResponse loginResponse = new LoginResponse();
        loginInteractor.checkPasswordFormat(pass, loginResponse);

        assertTrue(!loginResponse.wrongPassword);
    }

    @Test
    public void checkPasswordFormat_with_inValid_Pass(){
        String pass = "teste";
        LoginResponse loginResponse = new LoginResponse();
        loginInteractor.checkPasswordFormat(pass, loginResponse);

        assertTrue(loginResponse.wrongPassword);
    }


    private class LoginPresenterOutputSpy implements LoginPresenterInput {

        boolean responseErrorInvalidFieldsIsCalled = false;
        boolean responseLoginIsCalled = false;
        boolean responseErrorLoginIsCalled = false;
        boolean responseSavedUserIsCalled = false;


        @Override
        public void responseLogin(LoginResponse response) {
            responseLoginIsCalled = true;
        }

        @Override
        public void responseErrorLogin(ErrorResponse error) {
            responseErrorLoginIsCalled = true;
        }

        @Override
        public void responseErrorInvalidFields(LoginResponse response) {
            responseErrorInvalidFieldsIsCalled = true;
        }

        @Override
        public void responseSavedUser(LoginResponse response) {
            responseSavedUserIsCalled = true;
        }
    }

    private class LoginWorkerOutputSpy implements LoginWorkerInput {

        boolean loginIsCalled = false;
        boolean setUserFromSharedPreferencesIsCalled = false;
        boolean getUserFromSharedPreferencesIsCalled = false;
        boolean loginSucessWithError = false;
        boolean loginSucess = false;
        boolean loginFailure = false;

        @Override
        public void login(LoginRequest request, RequestListener<LoginResponse> responseListener) {
            loginIsCalled = true;
            if(loginSucess) {
                LoginResponse loginResponse = new LoginResponse();
                UserAccount userAccount = new UserAccount();
                userAccount.agency = "1";
                loginResponse.userAccount = userAccount;
                responseListener.onSuccess(loginResponse);
            }
            if(loginSucessWithError){
                responseListener.onFailure(new LoginResponse());
            }
            if(loginFailure) {
                responseListener.onFailure(new ErrorResponse("Error"));
            }
        }

        @Override
        public LoginResponse getUserFromSharedPreferences(Context context) {
            getUserFromSharedPreferencesIsCalled = true;
            LoginResponse loginResponse = new LoginResponse();
            LoginModel loginModel = new LoginModel("teste@teste.com.br", "Test@123");
            loginResponse.loginModel = loginModel;
            return loginResponse;
        }

        @Override
        public void setUserFromSharedPreferences(Context context, LoginRequest loginRequest) {
            setUserFromSharedPreferencesIsCalled = true;
        }
    }
}


