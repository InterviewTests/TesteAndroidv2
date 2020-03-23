package com.bankapp.loginScreen;

import android.content.Context;
import android.os.Build;

import androidx.test.InstrumentationRegistry;

import com.bankapp.helper.ConstantsHelper;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = Build.VERSION_CODES.KITKAT)
public class LoginWorkerUnitTest {

    Context context;
    LoginWorker loginWorker;

    @Before
    public void before() {
        context = InstrumentationRegistry.getTargetContext();
        loginWorker = new LoginWorker();
    }

    @Test
    public void setUserFromSharedPreferences_shouldSave_sharedPreferences() throws Exception {
        String testUser = "User test";
        String testPass = "PassTest";

        LoginRequest loginRequest = new LoginRequest();
        LoginModel loginModel = new LoginModel(testUser, testPass);
        loginRequest.loginModel = loginModel;

        loginWorker.setUserFromSharedPreferences(context, loginRequest);

        LoginResponse loginResponse = loginWorker.getUserFromSharedPreferences(context);

        assertTrue(loginResponse.loginModel.user.equals(testUser) && loginResponse.loginModel.password.equals(testPass));
    }

    @After
    public void after() {
        loginWorker.preferences.removeValue(ConstantsHelper.USER_PREF);
        loginWorker.preferences.removeValue(ConstantsHelper.PASS_PREF);
    }
}
