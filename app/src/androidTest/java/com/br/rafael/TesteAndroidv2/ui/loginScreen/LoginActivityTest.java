package com.br.rafael.TesteAndroidv2.ui.loginScreen;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.br.rafael.TesteAndroidv2.R;
import com.br.rafael.TesteAndroidv2.loginScreen.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void erro_loginMessage() {
        onView(withId(R.id.edt_user)).check(matches(isDisplayed()));
        onView(withId(R.id.edt_user)).perform(typeText("1233"),closeSoftKeyboard());
        onView(withId(R.id.btnLogar)).perform(click());

        onView(allOf(withId(com.google.android.material.R.id.snackbar_text), withText(R.string.error_user)))
                .check(matches(isDisplayed()));
    }

    @Test
    public void validateLoginDataMailInvalid() {

        onView(withId(R.id.edt_user))
                .perform(typeText("jefferson@.com"),  closeSoftKeyboard());

        onView(withId(R.id.btnLogar))
                .perform(click() , closeSoftKeyboard());

        onView(allOf(withId(com.google.android.material.R.id.snackbar_text), withText(R.string.error_user)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void validateLoginDataCpfInvalid() {

        onView(withId(R.id.edt_user))
                .perform(typeText("1111111111"),  closeSoftKeyboard());


        onView(withId(R.id.btnLogar))
                .perform(click() , closeSoftKeyboard());


        onView(allOf(withId(com.google.android.material.R.id.snackbar_text), withText(R.string.error_user)))
                .check(matches(isDisplayed()));

    }

    @Test
    public void validateLoginDataSucessLogin() {

        onView(withId(R.id.edt_user))
                .perform(typeText("oliveira@gmail.com"),  closeSoftKeyboard());

        onView(withId(R.id.edt_password))
                .perform(typeText("Q@") , closeSoftKeyboard());


        onView(withId(R.id.btnLogar))
                .perform(click());

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()));

    }



}
