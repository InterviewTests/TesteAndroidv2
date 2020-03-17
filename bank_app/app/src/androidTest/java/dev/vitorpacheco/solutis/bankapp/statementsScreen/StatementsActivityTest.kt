package dev.vitorpacheco.solutis.bankapp.statementsScreen

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.content.Intent
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vitorpacheco.solutis.bankapp.EspressoHelper
import dev.vitorpacheco.solutis.bankapp.EspressoHelper.dismissKeyguard
import dev.vitorpacheco.solutis.bankapp.R
import dev.vitorpacheco.solutis.bankapp.loginScreen.LoginActivity
import dev.vitorpacheco.solutis.bankapp.loginScreen.UserAccount
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal


@RunWith(AndroidJUnit4::class)
class StatementsActivityTest {

    @get: Rule
    val intentsTestRule = IntentsTestRule(StatementsActivity::class.java)

    @Test
    fun test_isActivityInView() {
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

        dismissKeyguard()

        IdlingRegistry.getInstance().register(activity.idlingResource)

        onView(withId(R.id.statements)).check(matches(isDisplayed()))
        intended(hasComponent(StatementsActivity::class.java.name))

        IdlingRegistry.getInstance().unregister(activity.idlingResource)
    }

    @Test
    fun test_isAccountInIntent() {
        val account = UserAccount(
            userId = 1,
            name = "Test User",
            bankAccount = "1231412",
            agency = "1123",
            balance = BigDecimal(300)
        )

        val intent = Intent()
        intent.putExtra("account", account)

        val result = ActivityResult(Activity.RESULT_OK, intent)

        intending(hasExtra("account", account)).respondWith(result)
    }

}

