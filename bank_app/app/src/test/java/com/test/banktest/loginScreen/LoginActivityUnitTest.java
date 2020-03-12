package com.test.banktest.loginScreen;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {

    @Test
    public void LoginActivity_ShouldNOT_be_Null(){
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        Assert.assertNotNull(activity);
    }

    @Test
    public void LoginActivity_Should_call_getLastUser(){
        ActivityController<LoginActivity> activityController = Robolectric.buildActivity(LoginActivity.class);
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        activityController.get().output = loginActivityOutputSpy;

        activityController.setup();

        Assert.assertTrue(loginActivityOutputSpy.getLastUserIsCalled);
    }

    @Test
    public void LoginActivity_Should_call_login(){
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        activity.output = loginActivityOutputSpy;

        activity.btLogin.performClick();

        Assert.assertTrue(loginActivityOutputSpy.loginIsCalled);
    }

    private class LoginActivityOutputSpy implements LoginInteractorInput {

        boolean loginIsCalled = false;
        boolean getLastUserIsCalled = false;
        LoginRequest loginRequestCopy;

        @Override
        public void login(LoginRequest request) {
            loginIsCalled = true;
            loginRequestCopy = request;
        }

        @Override
        public void getLastUser(LoginRequest request) {
            getLastUserIsCalled = true;
            loginRequestCopy = request;
        }
    }

}
