package com.example.testeandroidv2.loginScreen;

import org.junit.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.ref.WeakReference;

@RunWith(RobolectricTestRunner.class)
@Config(sdk=19)
public class LoginActivityUnitTest {

    private LoginActivity activity;
    @Before
    public void setUp(){
        activity = Robolectric.buildActivity(LoginActivity.class).create().resume().get();
    }

    @Test
    public void loginActivity_ShouldNOT_be_Null(){
        Assert.assertNotNull(activity);
    }

    @Test
    public void login_shouldCall_fetchMetaData(){
        // Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        activity.output = loginActivityOutputSpy;
        // When
        activity.login();
        // Then
        Assert.assertTrue(loginActivityOutputSpy.fetchLoginMetaDataIsCalled);
    }

    @Test
    public void login_with_validInput_shouldCall_displayLoginData_listOfLoginViewModel_shouldNOT_Empty_And_error_should_Null(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginPresenter loginPresenter = new LoginPresenter();

        // Setup Double Test
        activity.login = new LoginModel("111.111.111-11", "A@s");
        loginPresenter.output = new WeakReference<>(activity);
        loginInteractor.callback = null;
        loginInteractor.output = loginPresenter;

        // When
        activity.output = loginInteractor;
        activity.login();

        // Then
        Assert.assertNull(activity.error);
        Assert.assertTrue(activity.listOfLoginViewModel.size() > 0);
    }

    @Test
    public void login_with_invalidInput_shouldCall_displayLoginData_listOfLoginViewModel_should_Empty_And_error_shouldNOT_Null(){
        // Given
        LoginInteractor loginInteractor = new LoginInteractor();
        LoginPresenter loginPresenter = new LoginPresenter();

        // Setup Double Test
        activity.login = new LoginModel("aaaaaa", "A@s");
        loginPresenter.output = new WeakReference<>(activity);
        loginInteractor.callback = null;
        loginInteractor.output = loginPresenter;

        // When
        activity.output = loginInteractor;
        activity.login();

        // Then
        Assert.assertNotNull(activity.error);
        Assert.assertFalse(activity.listOfLoginViewModel.size() > 0);
    }
}

class LoginActivityOutputSpy implements LoginInteractorInput {

    boolean fetchLoginMetaDataIsCalled = false;

    @Override
    public void fetchLoginData(LoginRequest request) {
        fetchLoginMetaDataIsCalled = true;
    }
}
