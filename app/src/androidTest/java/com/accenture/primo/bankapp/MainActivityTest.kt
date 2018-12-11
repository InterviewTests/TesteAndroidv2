package com.accenture.primo.bankapp

import android.content.Intent
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.accenture.primo.bankapp.model.User
import com.accenture.primo.bankapp.ui.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java, true, false)


    @Test
    fun check_if_activity_has_data_loaded() {
        val objUser: User = User(1, "Jose", "123456-7", "0001", 1200.80f)

        val intent = Intent()
        intent.putExtra(EXTRA_KEY_LOGIN, objUser)
        mActivityTestRule.launchActivity(intent)

        // This is needed to wait activity loading data :(
        Thread.sleep(10000);

        val tblToolbar = onView(withId(R.id.tblToolbar)).check(matches(isDisplayed()))
        onView(withText(objUser.name)).check(matches(isDisplayed()))
    }
}
