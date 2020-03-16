package dev.vitorpacheco.solutis.bankapp.loginScreen

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vitorpacheco.solutis.bankapp.EspressoHelper.clearSharedPreferences
import dev.vitorpacheco.solutis.bankapp.EspressoHelper.dismissKeyguard
import dev.vitorpacheco.solutis.bankapp.EspressoHelper.getCurrentActivity
import dev.vitorpacheco.solutis.bankapp.EspressoHelper.hasTextInputLayoutErrorText
import dev.vitorpacheco.solutis.bankapp.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Test
    fun test_isActivityInVIew() {
        launch(LoginActivity::class.java)

        onView(withId(R.id.login)).check(matches(isDisplayed()))
    }

    @Test
    fun test_userFieldError() {
        launch(LoginActivity::class.java)

        clearSharedPreferences()

        onView(withId(R.id.login)).check(matches(isDisplayed()))

        onView(withId(R.id.userField)).perform(
            clearText(),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordField)).perform(
            clearText(),
            typeText("Test@1"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.userFieldLayout)).check(
            matches(
                hasTextInputLayoutErrorText(
                    getCurrentActivity()?.getString(R.string.error_invalid_user)
                )
            )
        )

        onView(withId(R.id.userField)).perform(
            clearText(),
            typeText("test_user"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.userFieldLayout)).check(
            matches(
                hasTextInputLayoutErrorText(
                    getCurrentActivity()?.getString(R.string.error_invalid_user)
                )
            )
        )

        onView(withId(R.id.userField)).perform(
            clearText(),
            typeText("47043374051"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.userFieldLayout)).check(
            matches(
                hasTextInputLayoutErrorText(
                    getCurrentActivity()?.getString(R.string.error_invalid_user)
                )
            )
        )

        onView(withId(R.id.userFieldLayout)).check(matches(isEnabled()))
        onView(withId(R.id.passwordFieldLayout)).check(matches(isEnabled()))
        onView(withId(R.id.loginButton)).check(matches(isEnabled()))
    }

    @Test
    fun test_passwordFieldError() {
        launch(LoginActivity::class.java)

        clearSharedPreferences()

        onView(withId(R.id.login)).check(matches(isDisplayed()))

        onView(withId(R.id.userField)).perform(
            clearText(),
            typeText("test_user@example.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordField)).perform(
            clearText(),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.passwordFieldLayout)).check(
            matches(
                hasTextInputLayoutErrorText(
                    getCurrentActivity()?.getString(R.string.error_invalid_password)
                )
            )
        )

        onView(withId(R.id.passwordField)).perform(
            clearText(),
            typeText("12331"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.passwordFieldLayout)).check(
            matches(
                hasTextInputLayoutErrorText(
                    getCurrentActivity()?.getString(R.string.error_invalid_password)
                )
            )
        )

        onView(withId(R.id.passwordField)).perform(
            clearText(),
            typeText("123asd31"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.passwordFieldLayout)).check(
            matches(
                hasTextInputLayoutErrorText(
                    getCurrentActivity()?.getString(R.string.error_invalid_password)
                )
            )
        )

        onView(withId(R.id.passwordField)).perform(
            clearText(),
            typeText("@#asd"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        onView(withId(R.id.passwordFieldLayout)).check(
            matches(
                hasTextInputLayoutErrorText(
                    getCurrentActivity()?.getString(R.string.error_invalid_password)
                )
            )
        )

        onView(withId(R.id.userFieldLayout)).check(matches(isEnabled()))
        onView(withId(R.id.passwordFieldLayout)).check(matches(isEnabled()))
        onView(withId(R.id.loginButton)).check(matches(isEnabled()))
    }

    @Test
    fun test_validUserAndPassword() {
        launch(LoginActivity::class.java)
        val activity = getCurrentActivity() as LoginActivity

        clearSharedPreferences()

        onView(withId(R.id.login)).check(matches(isDisplayed()))

        onView(withId(R.id.userField)).perform(
            clearText(),
            typeText("test_user@example.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordField)).perform(
            clearText(),
            typeText("Test@1"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        dismissKeyguard()

        IdlingRegistry.getInstance().register(activity.idlingResource)

        onView(withId(R.id.statements)).check(matches(isDisplayed()))

        IdlingRegistry.getInstance().unregister(activity.idlingResource)
    }

    @Test
    fun test_recoverLastLoggedUser() {
        launch(LoginActivity::class.java)
        val activity = getCurrentActivity() as LoginActivity

        clearSharedPreferences()

        onView(withId(R.id.login)).check(matches(isDisplayed()))

        onView(withId(R.id.userField)).perform(
            clearText(),
            typeText("test_user@example.com"),
            closeSoftKeyboard()
        )
        onView(withId(R.id.passwordField)).perform(
            clearText(),
            typeText("Test@1"),
            closeSoftKeyboard()
        )

        onView(withId(R.id.loginButton)).perform(click())

        dismissKeyguard()

        IdlingRegistry.getInstance().register(activity.idlingResource)

        onView(withId(R.id.statements)).check(matches(isDisplayed()))

        IdlingRegistry.getInstance().unregister(activity.idlingResource)

        launch(LoginActivity::class.java)

        onView(withId(R.id.login)).check(matches(isDisplayed()))

        onView(withId(R.id.userField)).check(matches(withText("test_user@example.com")))

        clearSharedPreferences()
    }

}