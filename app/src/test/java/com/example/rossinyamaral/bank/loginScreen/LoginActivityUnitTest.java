package com.example.rossinyamaral.bank.loginScreen;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class LoginActivityUnitTest {

    @Test
    public void LoginActivity_ShouldNOT_be_Null(){
        //Given
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchLoginMetaData(){
        //Given
        LoginActivityOutputSpy LoginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity LoginActivity = Robolectric.setupActivity(LoginActivity.class);
        // It must have called the onCreate earlier,
        // we are injecting the mock and calling the fetchMetaData to test our condition
        LoginActivity.output = LoginActivityOutputSpy;

        //When
        LoginActivity.output.fetchLoginData(null);

        //Then
        Assert.assertTrue(LoginActivityOutputSpy.fetchLoginMetaDataIsCalled);
    }

    @Test
    public void onCreate_Calls_fetchLoginMetaData_withCorrectData(){
        //Given
        LoginActivityOutputSpy LoginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity LoginActivity = Robolectric.setupActivity(LoginActivity.class);
        LoginActivity.output = LoginActivityOutputSpy;

        //When
        LoginRequest request = new LoginRequest();
        request.user = "test";
        request.password = "test";
        LoginActivity.output.fetchLoginData(request);

        //Then
        Assert.assertNotNull(LoginActivity);
        Assert.assertEquals(LoginActivityOutputSpy.loginRequestCopy, request);
    }



    private class LoginActivityOutputSpy implements LoginInteractorInput {

        boolean fetchLoginMetaDataIsCalled = false;
        LoginRequest loginRequestCopy;
        @Override
        public void fetchLoginData(LoginRequest request) {
            fetchLoginMetaDataIsCalled = true;
            loginRequestCopy = request;
        }
    }
}
