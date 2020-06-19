package com.example.testeandroidv2.loginScreen;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.example.testeandroidv2.Service.Api;
import com.example.testeandroidv2.Service.LoginService;
import com.google.gson.internal.LinkedTreeMap;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Retrofit;

public class LoginInteractorUnitTest {

    @Test
    public void fetchLoginData_with_validInput_Login_shouldCall_Worker_buildRequest(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "A12-345B";

        // Setup Test Double
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertTrue(loginWorkerInputSpy.isBuildRequestIsCalled);
    }

    @Test
    public void fetchLoginData_with_validInput_Login_shouldCall_Worker_getLoginResponse(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "A12-345B";

        // Setup Test Double
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertTrue(loginWorkerInputSpy.isGetLoginResponseIsCalled);
    }

    @Test
    public void fetchLoginData_with_validInput_Login_shouldReturn_NOT_be_Null(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "A12-345B";

        // Setup Test Double
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertNotNull(loginWorkerInputSpy.response.userAccount);
    }

    @Test
    public void fetchLoginData_with_invalidInputUser_Login_shouldNOT_Call_Worker_buildRequest(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-1";
        loginRequest.password = "A@s";

        // Setup Test Double
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertFalse(loginWorkerInputSpy.isBuildRequestIsCalled);
    }

    @Test
    public void fetchLoginData_with_invalidInputPassword_Login_shouldNOT_Call_Worker_buildRequest(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "1111";

        // Setup Test Double
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertFalse(loginWorkerInputSpy.isBuildRequestIsCalled);
    }

    @Test
    public void fetchLoginData_with_invalidInputUser_Login_shouldNOT_Call_Worker_getLoginResponse(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-1";
        loginRequest.password = "A@s";

        // Setup Test Double
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertFalse(loginWorkerInputSpy.isGetLoginResponseIsCalled);
    }

    @Test
    public void fetchLoginData_with_invalidInputPassword_Login_shouldNOT_Call_Worker_getLoginResponse(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "1111";

        // Setup Test Double
        loginInteractor.output = new LoginPresenterInputSpy();
        LoginWorkerInputSpy loginWorkerInputSpy = new LoginWorkerInputSpy();
        loginInteractor.setLoginWorkerInput(loginWorkerInputSpy);

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertFalse(loginWorkerInputSpy.isGetLoginResponseIsCalled);
    }

    @Test
    public void fetchLoginData_with_validInput_shouldCall_presentLoginData(){
        // When
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "A12-345B";

        // Setup Test Double
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.callback = null;
        loginInteractor.output = loginPresenterInputSpy;

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertTrue("When the valid input is passed to LoginInteractor Then presentLoginData should be called with erro null", loginPresenterInputSpy.presenterLoginDataIsCalled);
    }

    @Test
    public void fetchLoginData_with_validInput_shouldCall_presentLoginData_without_error(){
        // When
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "A12-345B";

        // Setup Test Double
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.callback = null;
        loginInteractor.output = loginPresenterInputSpy;

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Object error = loginPresenterInputSpy.loginResponse.error;
        if(error instanceof LinkedTreeMap)
            //noinspection rawtypes
            Assert.assertEquals(0, ((LinkedTreeMap) loginPresenterInputSpy.loginResponse.error).size());
        else
            Assert.assertNull(loginPresenterInputSpy.loginResponse.error);
    }

    @Test
    public void fetchLoginData_with_invalidInputUser_shouldCall_presentLoginData_with_error(){
        // When
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-1";
        loginRequest.password = "A12-345B";

        // Setup Test Double
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.callback = null;
        loginInteractor.output = loginPresenterInputSpy;

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertNotNull(loginPresenterInputSpy.loginResponse.error);
    }

    @Test
    public void fetchLoginData_with_invalidInputPassword_shouldCall_presentLoginData_with_error(){
        // When
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "111.111.111-11";
        loginRequest.password = "1111";

        // Setup Test Double
        LoginPresenterInputSpy loginPresenterInputSpy = new LoginPresenterInputSpy();
        loginInteractor.callback = null;
        loginInteractor.output = loginPresenterInputSpy;

        // When
        loginInteractor.fetchLoginData(loginRequest);

        // Then
        Assert.assertNotNull(loginPresenterInputSpy.loginResponse.error);
    }
}

class LoginWorkerInputSpy implements LoginWorkerInput {

    boolean isBuildRequestIsCalled = false;
    boolean isGetLoginResponseIsCalled = false;
    private Call<JsonObject> call;
    LoginResponse response;

    @Override
    public void buildRequest(LoginRequest request) {
        isBuildRequestIsCalled = true;
        Retrofit api = Api.getRetrofitInstance("https://bank-app-test.herokuapp.com/api/");
        LoginService loginService = api.create(LoginService.class);
        call = loginService.authentication(request.user, request.password);
    }

    @Override
    public LoginResponse getLoginResponse(Callback<JsonObject> callback) {
        isGetLoginResponseIsCalled = true;
        try {
            response = new Gson().fromJson(call.execute().body(), LoginResponse.class);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

class LoginPresenterInputSpy implements LoginPresenterInput {

    boolean presenterLoginDataIsCalled = false;
    LoginResponse loginResponse;

    @Override
    public void presentLoginData(LoginResponse response) {
        presenterLoginDataIsCalled = true;
        loginResponse = response;
    }
}
