package br.com.satandertest.views;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.satandertest.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void clickLoginButton_shouldVerifyCredentials() {

        onView(ViewMatchers.withId(R.id.tv_user)).perform(typeText("teste_user"), closeSoftKeyboard());
        onView(withId(R.id.tv_password)).perform(typeText("Test@1"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
    }
}
