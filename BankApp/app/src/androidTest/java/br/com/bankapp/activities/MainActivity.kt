package br.com.bankapp.activities

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import br.com.bankapp.BaseTest
import br.com.bankapp.R
import br.com.bankapp.helpers.*
import br.com.bankapp.helpers.ErrorStatementDispatcher
import br.com.bankapp.helpers.RequestDispatcher
import br.com.bankapp.ui.login.LoginActivity
import br.com.bankapp.ui.main.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivity : BaseTest() {

    @get:Rule
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java)

    @Test
    fun attemptLoginSuccess() {
        mockWebServer = MockWebServer().apply {
            dispatcher = RequestDispatcher()
            start(8080)
        }

        val user = "teste@email.com"
        Espresso.onView(ViewMatchers.withId(R.id.input_user))
            .perform(ViewActions.clearText(), ViewActions.typeText(user))

        val password = "1234K@78"
        Espresso.onView(ViewMatchers.withId(R.id.input_password))
            .perform(ViewActions.clearText(), ViewActions.typeText(password), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.login_button))
            .perform(ViewActions.click())

        sleep(10000L)

        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.name))

        Espresso.onView(ViewMatchers.withText("Jose da Silva Teste"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("2050 / 01.231456-4"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.balance_text))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

        Espresso.onView(ViewMatchers.withId(R.id.statements_recyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.statements_recyclerview))
            .check(RecyclerViewItemCountAssertion(9))
    }

    @Test
    fun attemptLoginSuccess_getStatementsFail() {
        mockWebServer = MockWebServer().apply {
            dispatcher = ErrorStatementDispatcher()
            start(8080)
        }

        val user = "teste@email.com"
        Espresso.onView(ViewMatchers.withId(R.id.input_user))
            .perform(ViewActions.clearText(), ViewActions.typeText(user))

        val password = "1234K@78"
        Espresso.onView(ViewMatchers.withId(R.id.input_password))
            .perform(ViewActions.clearText(), ViewActions.typeText(password), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.login_button))
            .perform(ViewActions.click())

        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.name))

        Espresso.onView(ViewMatchers.withText("Jose da Silva Teste"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("2050 / 01.231456-4"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withId(R.id.balance_text))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.withText(""))))

        // Is toast displayed and is the message correct?
        Espresso.onView(ViewMatchers.withText(R.string.text_main_generic_error))
            .inRoot(ToastMatcher())
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun attemptLoginSuccess_logoutSuccess() {
        mockWebServer = MockWebServer().apply {
            dispatcher = RequestDispatcher()
            start(8080)
        }

        val user = "teste@email.com"
        Espresso.onView(ViewMatchers.withId(R.id.input_user))
            .perform(ViewActions.clearText(), ViewActions.typeText(user))

        val password = "1234K@78"
        Espresso.onView(ViewMatchers.withId(R.id.input_password))
            .perform(ViewActions.clearText(), ViewActions.typeText(password), ViewActions.closeSoftKeyboard())

        Espresso.onView(ViewMatchers.withId(R.id.login_button))
            .perform(ViewActions.click())

        sleep(10000L)

        Espresso.onView(ViewMatchers.withId(R.id.exit_button))
            .perform(ViewActions.click())

        sleep(null)

        Intents.intended(IntentMatchers.hasComponent(LoginActivity::class.java.name))
    }
}