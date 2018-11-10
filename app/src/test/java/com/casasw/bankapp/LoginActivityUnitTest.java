package com.casasw.bankapp;

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
    public void setUp(){}
    @After
    public void tearDown(){}

    @Test
    public void LoginActivity_ShouldNOT_be_Null(){
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);

        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchLoginData(){
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);

        loginActivity.output = loginActivityOutputSpy;

        loginActivity.fetchLoginData();

        Assert.assertTrue(loginActivityOutputSpy.fetchLoginDataIsCalled);
    }

    @Test
    public void onCreate_Calls_fetchLoginData_withCorrectData(){
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.output = loginActivityOutputSpy;

        loginActivity.fetchLoginData();

        Assert.assertNotNull(loginActivity);
        Assert.assertTrue(loginActivityOutputSpy.loginRequestCopy.isLogOn);

    }

    public static class LoginActivityOutputSpy implements LoginInteractorInput {
        boolean fetchLoginDataIsCalled = false;
        LoginRequest loginRequestCopy;
        @Override
        public void fetchLoginData(LoginRequest request) {
            fetchLoginDataIsCalled = true;
            loginRequestCopy = request;
        }
    }
}
