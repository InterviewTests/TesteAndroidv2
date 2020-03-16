package dev.vitorpacheco.solutis.bankapp.statementsScreen

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vitorpacheco.solutis.bankapp.EspressoHelper
import dev.vitorpacheco.solutis.bankapp.R
import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StatementsActivityTest {

    @Test
    fun test_isActivityInVIew() {
        launch(LoginActivity::class.java)
        val activity = EspressoHelper.getCurrentActivity() as LoginActivity

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

        IdlingRegistry.getInstance().register(activity.idlingResource)

        onView(withId(R.id.statements)).check(matches(isDisplayed()))

        IdlingRegistry.getInstance().unregister(activity.idlingResource)
    }

}

