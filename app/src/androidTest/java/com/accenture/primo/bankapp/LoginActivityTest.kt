package com.accenture.primo.bankapp

import android.app.Activity
import android.app.Instrumentation.ActivityResult
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intending
import android.support.test.espresso.intent.matcher.IntentMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.accenture.primo.bankapp.ui.main.MainActivity
import com.accenture.primo.bankapp.ui.login.LoginActivity
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private fun getResourceString(id: Int): String {
        val context = InstrumentationRegistry.getTargetContext()
        return context.resources.getString(id)
    }

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun user_and_password_isOK() {
        //mActivityTestRule.launchActivity(Intent());

        val matcher = IntentMatchers.hasComponent(MainActivity::class.java.name)
        val txtUserEdit = onView(withId(R.id.txtUserEdit)).check(matches(isDisplayed()))
        val txtPasswordEdit = onView(withId(R.id.txtPasswordEdit)).check(matches(isDisplayed()))
        val btnLogin = onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))

        Intents.init()
        txtUserEdit.perform(scrollTo(), replaceText("jose@bankapp.com"), closeSoftKeyboard())
        txtPasswordEdit.perform(scrollTo(), replaceText("1qA@"), closeSoftKeyboard())

        btnLogin.perform(click())

        // This is needed to wait activity loading :(
        Thread.sleep(10000);

        val result = ActivityResult(Activity.RESULT_OK, null)
        intending(matcher).respondWith(result)

        Intents.intended(matcher);
        Intents.release();
    }

    @Test
    fun invalid_CPF() {
        //mActivityTestRule.launchActivity(Intent());

        val txtUserEdit = onView(withId(R.id.txtUserEdit)).check(matches(isDisplayed()))
        val btnLogin = onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))

        txtUserEdit.perform(scrollTo(), replaceText("111.111.111-11"), closeSoftKeyboard())
        btnLogin.perform(click())

        onView(withText(getResourceString(R.string.user_error))).check(matches(isDisplayed()))
    }

    @Test
    fun invalid_Password() {
        val txtPasswordEdit = onView(withId(R.id.txtPasswordEdit)).check(matches(isDisplayed()))
        val btnLogin = onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))

        txtPasswordEdit.perform(scrollTo(), replaceText("aaaa"), closeSoftKeyboard())
        btnLogin.perform(click())
        onView(withText(R.string.password_error)).check(matches(isDisplayed()))
    }
}
