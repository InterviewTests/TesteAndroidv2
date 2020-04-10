package pt.felipegouveia.bankapp.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test

import org.junit.runner.RunWith
import pt.felipegouveia.bankapp.R

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainMenuUiTest {

    @get: Rule
    val activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java)

    @Test
    fun test() {
        onView(withId(R.id.main_nav_container)).check(matches(isDisplayed()))
    }
}