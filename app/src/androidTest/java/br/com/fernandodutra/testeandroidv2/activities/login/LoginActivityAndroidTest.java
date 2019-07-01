package br.com.fernandodutra.testeandroidv2.activities.login;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.fernandodutra.testeandroidv2.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by Fernando Dutra
 * User: Fernando Dutra
 * Date: 01/07/2019
 * Time: 10:48
 * TesteAndroidv2_CleanCode
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityAndroidTest {

    @Rule
    public ActivityTestRule mActivityRule =
            new ActivityTestRule(LoginActivity.class);

    @Test
    public void setupComponents_isValid() {
        // verify is dysplayed
        onView(withId(R.id.login_activity_et_username)).check(matches(isDisplayed()));
        onView(withId(R.id.login_activity_et_password)).check(matches(isDisplayed()));
        onView(withId(R.id.login_activity_btn_login)).check(matches(isDisplayed()));
    }

    @Test
    public void type_Email_And_Password_Click_Button_ValidAccess() {
        String email = "fernando@gmail.com";
        String password = "@Fer123";

        //type in email
        onView(withId(R.id.login_activity_et_username)).perform(typeText(email), closeSoftKeyboard());

        //type in password
        onView(withId(R.id.login_activity_et_password)).perform(typeText(password), closeSoftKeyboard());

        //click on login button
        onView(withId(R.id.login_activity_btn_login)).perform(click());
    }

    @Test
    public void type_CPF_And_Password_Click_Button_ValidAccess() {
        String email = "367.464.058-93";
        String password = "@Fer123";

        //type in email
        onView(withId(R.id.login_activity_et_username)).perform(typeText(email), closeSoftKeyboard());

        //type in password
        onView(withId(R.id.login_activity_et_password)).perform(typeText(password), closeSoftKeyboard());

        //click on login button
        onView(withId(R.id.login_activity_btn_login)).perform(click());
    }

    @Test
    public void type_CPF_And_Password_Click_Button_Invalid_CPF_InvalidAccess() {
        String email = "367.464.058-94";
        String password = "@Fer123";

        //type in email
        onView(withId(R.id.login_activity_et_username)).perform(typeText(email), closeSoftKeyboard());

        //type in password
        onView(withId(R.id.login_activity_et_password)).perform(typeText(password), closeSoftKeyboard());

        //click on login button
        onView(withId(R.id.login_activity_btn_login)).perform(click());

        //valid toast message
        onView(withText(R.string.username_required)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void type_Email_And_Password_Click_Button_Invalid_Email_InvalidAccess() {
        String email = "fernando.com";
        String password = "@Fer123";

        //type in email
        onView(withId(R.id.login_activity_et_username)).perform(typeText(email), closeSoftKeyboard());

        //type in password
        onView(withId(R.id.login_activity_et_password)).perform(typeText(password), closeSoftKeyboard());

        //click on login button
        onView(withId(R.id.login_activity_btn_login)).perform(click());

        //valid toast message
        onView(withText(R.string.username_required)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void type_Email_And_Password_Click_Button_Invalid_Password_InvalidAccess() {
        String email = "fernando@gmail.com";
        String password = "123456";

        //type in email
        onView(withId(R.id.login_activity_et_username)).perform(typeText(email), closeSoftKeyboard());

        //type in password
        onView(withId(R.id.login_activity_et_password)).perform(typeText(password), closeSoftKeyboard());

        //click on login button
        onView(withId(R.id.login_activity_btn_login)).perform(click());

        //valid toast message
        onView(withText(R.string.password_required)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void type_CPF_And_Password_Click_Button_Invalid_Password_InvalidAccess() {
        String email = "367.464.058-93";
        String password = "123456";

        //type in email
        onView(withId(R.id.login_activity_et_username)).perform(typeText(email), closeSoftKeyboard());

        //type in password
        onView(withId(R.id.login_activity_et_password)).perform(typeText(password), closeSoftKeyboard());

        //click on login button
        onView(withId(R.id.login_activity_btn_login)).perform(click());

        //valid toast message
        onView(withText(R.string.password_required)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void type_CPF_And_Password_Click_Button_Invalid_InvalidAccess() {
        String email = "";
        String password = "";

        //type in email
        onView(withId(R.id.login_activity_et_username)).perform(typeText(email), closeSoftKeyboard());

        //type in password
        onView(withId(R.id.login_activity_et_password)).perform(typeText(password), closeSoftKeyboard());

        //click on login button
        onView(withId(R.id.login_activity_btn_login)).perform(click());

        //valid toast message
        onView(withText(R.string.password_required)).inRoot(withDecorView(not(is(mActivityRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

}