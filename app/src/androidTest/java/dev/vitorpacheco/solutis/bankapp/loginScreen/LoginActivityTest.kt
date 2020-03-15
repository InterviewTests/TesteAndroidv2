package dev.vitorpacheco.solutis.bankapp.loginScreen

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vitorpacheco.solutis.bankapp.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Test
    fun test_isActivityInVIew() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)

        onView(withId(R.id.login)).check(matches(isDisplayed()))
    }

}