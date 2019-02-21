package com.avanade.testesantander2.loginScreen;

import android.content.Context;
import androidx.annotation.NonNull;
import android.widget.Button;

import com.avanade.testesantander2.UserAccount;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @NonNull
    private LoginViewModel getLoginViewModel() {
        LoginViewModel loginViewModel = new LoginViewModel();
        UserAccount u = new UserAccount(1, "Teste", "070707", "0707", -17.33);
        loginViewModel.userAccount = u;
        return loginViewModel;
    }

    @Test
    public void LoginActivity_ShouldNOT_be_Null() {
        //Given
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        //When

        // Then
        Assert.assertNotNull(loginActivity);
    }

    @Test
    public void onResume_shouldCall_checkUsuarioSalvo() {
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.output = loginActivityOutputSpy;

        //When
        loginActivity.checaUsuarioSalvo();

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertTrue(loginActivityOutputSpy.callCheckUsuarioSalvo);

        // Resposta recebida pelo presenter em SetUsuario
        Assert.assertNotNull(loginActivity.loginRequest);
    }

    @Test
    public void onClickButton_shouldCall_postLogin() {
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the checaUsuarioSalvo to test our condition
        loginActivity.output = loginActivityOutputSpy;

        // Stub to Click
        Button button = null;

        //When
        loginActivity.clickLogin(button);

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertTrue(loginActivityOutputSpy.callPostLogin);
    }

    @Test
    public void onCall_postLogin_withCorrectData() {
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the checaUsuarioSalvo to test our condition
        loginActivity.output = loginActivityOutputSpy;

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.user = "teste@teste.com";
        loginRequest.password = "Test@1";

        //When
        loginActivityOutputSpy.postLogin(loginActivity.getApplicationContext(), loginRequest);

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertTrue(loginActivityOutputSpy.callPostLogin);
    }

    @Test
    public void onCall_openHomeScreen() {
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the checaUsuarioSalvo to test our condition
        loginActivity.router = loginActivityOutputSpy;

        UserAccount userAccount = new UserAccount();

        //When
        loginActivity.openHomeScreen(userAccount);

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertTrue(loginActivityOutputSpy.callOpenHomeScreen);

    }


    // SPY CLASS -> para verificar calls
    private class LoginActivityOutputSpy implements LoginInteractorInput, LoginRouterInput {

        boolean callCheckUsuarioSalvo = false;
        boolean callPostLogin = false;
        LoginRouter router;

        @Override
        public void checkUsuarioSalvo(Context context) {
            callCheckUsuarioSalvo = true;
        }

        @Override
        public void postLogin(Context context, LoginRequest loginRequest) {
            callPostLogin = true;
        }

        boolean callOpenHomeScreen = false;

        @Override
        public void openHomeScreen(UserAccount userAccount) {
            callOpenHomeScreen = true;
        }
    }


}