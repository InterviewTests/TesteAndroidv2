package com.br.projetotestesantanter

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    @Rule
    @JvmField
    val rule = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun validateLoginDataPasswordInvalide() {

        onView(withId(R.id.edt_user))
            .perform(typeText("oliveira@gmail.com"),  closeSoftKeyboard())

        onView(withId(R.id.edt_password))
            .perform(typeText("123456") , closeSoftKeyboard())

        onView(withId(R.id.btnLogar))
            .perform(click() , closeSoftKeyboard())


        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.error_password)))
            .check(matches(isDisplayed()))

    }


    @Test
    fun validateLoginDataMailInvalid() {

        onView(withId(R.id.edt_user))
            .perform(typeText("jefferson@.com"),  closeSoftKeyboard())


        onView(withId(R.id.btnLogar))
            .perform(click() , closeSoftKeyboard())


        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.error_user)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun validateLoginDataCpfInvalid() {

        onView(withId(R.id.edt_user))
            .perform(typeText("1111111111"),  closeSoftKeyboard())


        onView(withId(R.id.btnLogar))
            .perform(click() , closeSoftKeyboard())


        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText(R.string.error_user)))
            .check(matches(isDisplayed()))

    }

    @Test
    fun validateLoginDataSucessLogin() {

        onView(withId(R.id.edt_user))
            .perform(typeText("oliveira@gmail.com"),  closeSoftKeyboard())

        onView(withId(R.id.edt_password))
            .perform(typeText("Q@") , closeSoftKeyboard())


        onView(withId(R.id.btnLogar))
            .perform(click() , closeSoftKeyboard())


        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))

    }
}