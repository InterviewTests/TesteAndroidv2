package com.testeandroidv2.loginScreen;

import org.junit.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class LoginActivityUnitTest {

    @Test
    public void loginActivity_ShouldNOT_be_Null(){
        // Given
        LoginActivity activity = new LoginActivity();
        // When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void onCreate_shouldCall_fetchLoginMetaData(){
        // Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity activity = new LoginActivity();
        activity.output = loginActivityOutputSpy;
        // When
        activity.fetchMetaData();
        // Then
        Assert.assertTrue(loginActivityOutputSpy.fetchLoginMetaDataIsCalled);
    }

    @Test
    public void onCreate_Calls_fetchHomeMetaData_withCorrectData(){
        // Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity activity = new LoginActivity();
        activity.output = loginActivityOutputSpy;
        // When
        activity.fetchMetaData();
        // Then
        Assert.assertNotNull(activity);
        Assert.assertTrue(loginActivityOutputSpy.fetchLoginMetaDataIsCalled);
    }
}

class LoginActivityOutputSpy implements LoginInteractorInput {

    boolean fetchLoginMetaDataIsCalled = false;

    @Override
    public void fetchLoginData(LoginRequest request) {
        fetchLoginMetaDataIsCalled = true;
    }
}
