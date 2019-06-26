package com.bilulo.androidtest04.login;

import android.widget.Button;
import android.widget.EditText;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.bilulo.androidtest04.R;
import com.bilulo.androidtest04.components.UserEditText;
import com.bilulo.androidtest04.data.model.response.LoginResponse;
import com.bilulo.androidtest04.ui.login.contract.LoginContract;
import com.bilulo.androidtest04.ui.login.view.LoginActivity;
import com.bilulo.androidtest04.utils.ValidationUtil;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4.class)
public class LoginActivityUnitTest {
    @Rule
    public ActivityTestRule<LoginActivity> rule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void LoginActivity_ShouldNOT_be_NULL() {
        assertNotNull(rule.getActivity());
    }

    @Test
    public void onClick_shouldCall_performLogin_withVALIDParameters() {
        LoginActivity activity = rule.getActivity();
        LoginInteractorSpy loginInteractorSpy = new LoginInteractorSpy();
        activity.interactor = loginInteractorSpy;

        //Clears edt_user input before assertion
        onView(withId(R.id.edt_user))
                .perform(clearText());

        onView(withId(R.id.edt_user))
                .perform(typeText("teste@teste.com"), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(typeText("A!."), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Assert.assertTrue(loginInteractorSpy.isPerformLoginCalled);
    }

    @Test
    public void onClick_shouldNOTCall_performLogin_withINVALIDParameters() {
        LoginActivity activity = rule.getActivity();
        LoginInteractorSpy loginInteractorSpy = new LoginInteractorSpy();
        activity.interactor = loginInteractorSpy;

        //Clears edt_user input before assertion
        onView(withId(R.id.edt_user))
                .perform(clearText());

        onView(withId(R.id.edt_user))
                .perform(typeText("testecxzcm,.;.@teste"), closeSoftKeyboard());
        onView(withId(R.id.edt_password))
                .perform(typeText("a65456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Assert.assertFalse(loginInteractorSpy.isPerformLoginCalled);
    }

    @Test
    public void login_validation_withINVALIDParameters() {
        LoginActivity activity = rule.getActivity();

        //TODO add more invalid user scenarios
        String[] invalidUsers = new String[]{null, "", "a", "ae;;", "adae@teste", "sda@sdad.'''",
                "111.333.444-11", "039.231.333", "11.33.2313.44" //...
        };
        for (String invalidUser : invalidUsers) {
            if (activity.isValidUser(invalidUser)) {
                fail("Validation for valid user failed.");
            }
        }
        //TODO add more invalid password scenarios
        String[] invalidPasswords = new String[]{null, "", "a", "ae;;", "adae@teste", "sda@sdad.",
                "111.333.444-11", "039.231.333", "11.33.2313.44", "AAAEAEAEA", "AEAEAEAEAE1321",
                "193993823", "testeTeste"  //...
        };
        for (String invalidPassword : invalidPasswords) {
            if (activity.isValidPassword(invalidPassword)) {
                fail("Validation for valid password failed.");
            }
        }
    }
    @Test
    public void login_validation_withVALIDParameters() {
        LoginActivity activity = rule.getActivity();
        //TODO add more valid user scenarios
        String[] validUsers = new String[]{"a@a.com", "agnaldorxzc@gmail.com", "aeaee231@aeea.com",
                "765.510.590-15", "adae@teste.uea", "sda@sdad.net", "529.406.130-95", "694.494.840-07" //...
        };
        for (String validUser : validUsers) {
            if (!activity.isValidUser(validUser)) {
                fail("Validation for valid user failed.");
            }
        }

        //TODO add more valid password scenarios
        String[] validPasswords = new String[]{"Aa.", "aE;;.", "Teste123!", "Ada@sdad.",
                "111.333.A444-11", "A039.231", "AEAE.33.2313.44", "AAAEAEAEA~", "AEAEAEAEAE1321]",
                "K1939933\\"  //...
        };
        for (String validPassword : validPasswords) {
            if (!activity.isValidPassword(validPassword)) {
                fail("Validation for valid password failed.");
            }
        }
    }

    class LoginInteractorSpy implements LoginContract.InteractorContract {
        boolean isPerformLoginCalled = false;
        boolean isSetLoginResponseCalled = false;
        LoginResponse loginResponseCopy;
        boolean isSuccessfulCopy;

        @Override
        public void performLogin(String user, String password) {
            isPerformLoginCalled = true;
        }

        @Override
        public void setLoginResponse(LoginResponse response, boolean isSuccessful) {
            isSetLoginResponseCalled = true;
            loginResponseCopy = response;
            isSuccessfulCopy = isSuccessful;
        }
    }
}
