package com.felipemsa.idletime

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.felipemsa.idletime.ui.login.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class LoginActivityTest: BaseTestActivity() {

    @get:Rule
    var activityRule: ActivityTestRule<LoginActivity> = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun login() {
        onView(withId(R.id.et_user)).perform(typeText("test_user"))
        onView(withId(R.id.et_pass)).perform(typeText("Test@1"))

        closeSoftKeyboard()

        onView(withId(R.id.btt_login)).perform(click())
        doWait(TimeUnit.SECONDS.toMillis(1))
    }
}
