package br.com.fernandodutra.testeandroidv2.activities.login;

import android.content.Context;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 27/06/2019
 * Time: 15:39
 * TesteAndroidv2_CleanCode
 */
@RunWith(RobolectricTestRunner.class)
public class LoginActivityTest {

    @Test
    public void loginActivity_activityNotNull() {
        //Given
        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
        //When

        // Then
        Assert.assertNotNull(activity);
    }

    @Test
    public void loginActivity_fetchMetaDataIsValid() {
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.loginInteractorInput = loginActivityOutputSpy;

        //When
        loginActivity.fetchMetaData();

        //Then
        Assert.assertTrue(loginActivityOutputSpy.fetchLoginMetaDataIsCalled);
    }

    @Test
    public void loginActivity_fetchMetaDataIsValidLogin() {
        //Given
        LoginActivityOutputSpy loginActivityOutputSpy = new LoginActivityOutputSpy();
        LoginActivity loginActivity = Robolectric.setupActivity(LoginActivity.class);
        loginActivity.loginInteractorInput = loginActivityOutputSpy;

        //When
        loginActivity.fetchMetaData();

        //Then
        Assert.assertNotNull(loginActivity);
        Assert.assertNotNull(loginActivityOutputSpy.loginRequestCopy.getLogin());
    }

    private class LoginActivityOutputSpy implements LoginInteractorInput {

        boolean fetchLoginMetaDataIsCalled = false;
        LoginRequest loginRequestCopy;

        @Override
        public void fetchLoginMetaData(Context context, LoginRequest request) {
            fetchLoginMetaDataIsCalled = true;
            loginRequestCopy = request;
        }
    }

}