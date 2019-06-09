package com.br.projetotestesantanter.refactor.loginScreen;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {

    LoginActivity activity;

    @Before
    public void setUp() {

        activity = Robolectric.buildActivity(LoginActivity.class)
                .create()
                .resume()
                .get();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void LoginActivity_ShouldNOT_be_Null() {
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchHomeMetaData() {

        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        activity.output = loginActivityOutputSpy;
        activity.prepareRequest();
        Assert.assertNotNull(loginActivityOutputSpy.loginRequest);

    }

    private class LoginActivityOutputSpy implements LoginInteractorInput {

        LoginModel.LoginRequest loginRequest;

        @Override
        public void fetchHomeMetaData(LoginModel.LoginRequest request) {

            this.loginRequest = request;
        }
    }
}
