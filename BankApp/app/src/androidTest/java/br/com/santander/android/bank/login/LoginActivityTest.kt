package br.com.santander.android.bank.login

import android.content.Intent
import android.preference.PreferenceManager
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import br.com.santander.android.bank.R
import br.com.santander.android.bank.login.presentation.LoginActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    private val rule = ActivityTestRule(LoginActivity::class.java, false, false)

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        preferences.edit().putString("account", null).commit()
        rule.launchActivity(Intent())
    }

    @Test
    fun withEmptyUser_shouldShowErrorMessage() {
        onView(withId(R.id.edt_user))
            .perform(typeText(""), closeSoftKeyboard())

        onView(withId(R.id.btn_login))
            .perform(click())

        onView(withId(R.id.edt_user))
            .check(matches(hasErrorText(rule.activity.getString(R.string.error_empty_user))))
    }

    @Test
    fun withEmptyPassword_shouldShowErrorMessage() {
        onView(withId(R.id.edt_user))
            .perform(typeText("a@a.com"), closeSoftKeyboard())

        onView(withId(R.id.btn_login))
            .perform(click())

        onView(withId(R.id.edt_password))
            .check(matches(hasErrorText(rule.activity.getString(R.string.error_empty_password))))
    }

    @Test
    fun whenInputInvalidUserType_shouldShowErrorMessage() {
        onView(withId(R.id.edt_user))
            .perform(typeText("1qw2e34"), closeSoftKeyboard())

        onView(withId(R.id.btn_login))
            .perform(click())

        onView(withId(R.id.edt_user))
            .check(matches(hasErrorText(rule.activity.getString(R.string.error_invalid_user))))
    }

    @Test
    fun whenInputInvalidPassword_shouldShowErrorMessage() {
        onView(withId(R.id.edt_user))
            .perform(typeText("a@a.com"))

        onView(withId(R.id.edt_password))
            .perform(typeText("123abc"), closeSoftKeyboard())

        onView(withId(R.id.btn_login))
            .perform(click())

        onView(withId(R.id.edt_password))
            .check(matches(hasErrorText(rule.activity.getString(R.string.error_invalid_password))))
    }
}