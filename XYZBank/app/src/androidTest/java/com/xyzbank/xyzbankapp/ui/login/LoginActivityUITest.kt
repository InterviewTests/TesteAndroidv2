package com.xyzbank.xyzbankapp.ui.login


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.xyzbank.xyzbankapp.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginActivityUITest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(LoginActivity::class.java)

    @Test
    fun loginActivityUITest() {
        val editText = onView(
            allOf(
                withId(R.id.username), withHint("User"),
                withParent(
                    allOf(
                        withId(R.id.container),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )

        val editText2 = onView(
            allOf(
                withId(R.id.password),
                withParent(
                    allOf(
                        withId(R.id.container),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )

        val button = onView(
            allOf(
                withId(R.id.login), withText("Login"),
                withParent(
                    allOf(
                        withId(R.id.container),
                        withParent(withId(android.R.id.content))
                    )
                ),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

//        editText.perform(typeText("12345678901"), closeSoftKeyboard())
//        editText.check(matches(withText("12345678901")))
//        editText.perform(clearText(), closeSoftKeyboard())
//        editText.perform(typeText("test_user@msn.com"), closeSoftKeyboard())
//        editText.check(matches(withText("test_user@msn.com")))
        editText2.perform(typeText("Test@1"), closeSoftKeyboard())
        editText2.check(matches(withText("Test@1")))
//        editText.perform(clearText(), closeSoftKeyboard())
        button.perform(click())

    }
}
