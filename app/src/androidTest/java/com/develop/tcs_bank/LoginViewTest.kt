package com.develop.tcs_bank

import androidx.navigation.NavController
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.develop.tcs_bank.presentation.login.NavigationRegistration
import com.develop.tcs_bank.presentation.main.Constants
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import android.content.SharedPreferences
import android.content.Intent
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginViewTest {

    var intent: Intent? = null
    lateinit var preferencesEditor: SharedPreferences
    var mockNavController = mock(NavController::class.java)

    @Rule
    @JvmField
    val rule = IntentsTestRule(NavigationRegistration::class.java)

    @Before
    fun setUp() {
        val targetContext = getInstrumentation().targetContext
        preferencesEditor = targetContext.getSharedPreferences(Constants.PREFS_NAME, 0)
    }

    @Test
      fun checkInitials(){

          onView(withId(R.id.edUser)).check(matches(isDisplayed()))
          onView(withId(R.id.edPass)).check(matches(isDisplayed()))

      }

    @Test
    fun checkLogin(){

        //var mockNavController = mock(NavController::class.java)


        onView(withId(R.id.edUser)).perform(clearText())
        onView(withId(R.id.edPass)).perform(clearText())

        onView(withId(R.id.edUser)).perform(typeText("teste@teste.com.br"), closeSoftKeyboard())
        onView(withId(R.id.edPass)).perform(typeText("aB1@"), closeSoftKeyboard())

        onView(withId(R.id.btnLogin)).perform(click())
        //verify(mockNavController).navigate(R.id.nav_data)

    }


}