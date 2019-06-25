package com.bilulo.androidtest04.login;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.view.LoginActivity;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class LoginActivityUnitTest {

    @Test
    public void LoginActivity_ShouldNOT_be_null(){
        ActivityScenario<LoginActivity> loginActivityScenario = ActivityScenario.launch(LoginActivity.class);
        loginActivityScenario.moveToState(Lifecycle.State.CREATED);
        loginActivityScenario.onActivity(new ActivityScenario.ActivityAction<LoginActivity>() {
            @Override
            public void perform(LoginActivity activity) {
                assertNotNull(activity);
            }
        });
    }

    /*@Test
    public void onClick_shouldCall_performLogin_withValidParameters(){
        //Given
        final LoginInteractorSpy loginInteractorSpy = new LoginInteractorSpy();
        ActivityScenario<LoginActivity> loginActivityScenario = ActivityScenario.launch(LoginActivity.class);
        loginActivityScenario.onActivity(new ActivityScenario.ActivityAction<LoginActivity>() {
            @Override
            public void perform(LoginActivity activity) {
                activity.interactor = loginInteractorSpy;
                activity.edtUser.setText("teste@teste.com");
                activity.edtPassword.setText("A!.");
                activity.onClick(activity.btnLogin);
                Assert.assertTrue(loginInteractorSpy.isPerformLoginCalled);
            }
        });
    } */

    private class LoginInteractorSpy implements LoginContract.InteractorContract {
        boolean isPerformLoginCalled = false;
        boolean isSetLoginResponseCalled = false;
        LoginResponse loginResponseCopy;
        boolean isSuccessfulCopy;

        @Override
        public void performLogin(String user, String password) {
            boolean isPerformLoginCalled = true;
        }

        @Override
        public void setLoginResponse(LoginResponse response, boolean isSuccessful) {
            boolean isSetLoginResponseCalled = true;
            loginResponseCopy = response;
            isSuccessfulCopy = isSuccessful;
        }
    }
}
