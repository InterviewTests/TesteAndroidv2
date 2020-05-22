package br.com.bankapp.activities

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import br.com.bankapp.BaseTest
import br.com.bankapp.R
import br.com.bankapp.helpers.ErrorDispatcher
import br.com.bankapp.helpers.RequestDispatcher
import br.com.bankapp.helpers.ToastMatcher
import br.com.bankapp.helpers.hasTextInputLayoutErrorText
import br.com.bankapp.ui.login.LoginActivity
import br.com.bankapp.ui.main.MainActivity
import okhttp3.mockwebserver.MockWebServer
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@MediumTest
class LoginActivity : BaseTest() {

    @get:Rule
    val intentsTestRule = IntentsTestRule(LoginActivity::class.java)


    @Test
    fun checkInvalidUserError() {
        val cpf = "33172455ada"

        onView(withId(R.id.input_user))
            .perform(clearText(), typeText(cpf), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.login_button))
            .perform(click())

        onView(withId(R.id.input_user_layout))
            .check(matches(hasTextInputLayoutErrorText("User must be a valid email or cpf")))
    }

    @Test
    fun checkEmptyUserError() {
        val cpf = ""

        onView(withId(R.id.input_user))
            .perform(clearText(), typeText(cpf), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.login_button))
            .perform(click())

        onView(withId(R.id.input_user_layout))
            .check(matches(hasTextInputLayoutErrorText("User is required")))
    }

    @Test
    fun checkInvalidPasswordError() {
        val password = "1234K56"

        onView(withId(R.id.input_password))
            .perform(clearText(), typeText(password), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.login_button))
            .perform(click())

        onView(withId(R.id.input_password_layout))
            .check(matches(
                hasTextInputLayoutErrorText("Password must have at least " +
                    "one capital letter, a special character and an alphanumeric character")
            ))
    }

    @Test
    fun checkEmptyPasswordError() {
        val password = ""

        onView(withId(R.id.input_password))
            .perform(clearText(), typeText(password), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.login_button))
            .perform(click())

        onView(withId(R.id.input_password_layout))
            .check(matches(hasTextInputLayoutErrorText("Password is required")))
    }

    @Test
    fun attemptLoginSuccess() {
        mockWebServer = MockWebServer().apply {
            dispatcher = RequestDispatcher()
            start(8080)
        }

        val user = "teste@email.com"
        onView(withId(R.id.input_user))
            .perform(clearText(), typeText(user))

        val password = "1234K@78"
        onView(withId(R.id.input_password))
            .perform(clearText(), typeText(password), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.login_button))
            .perform(click())

        sleep(null)

        Intents.intended(IntentMatchers.hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun attemptLoginFail() {
        mockWebServer = MockWebServer().apply {
            dispatcher = ErrorDispatcher()
            start(8080)
        }

        val user = "teste@email.com"
        onView(withId(R.id.input_user))
            .perform(clearText(), typeText(user))

        val password = "1234K@78"
        onView(withId(R.id.input_password))
            .perform(clearText(), typeText(password), ViewActions.closeSoftKeyboard())

        onView(withId(R.id.login_button))
            .perform(click())

        // Is toast displayed and is the message correct?
        onView(withText(R.string.text_login_generic_error)).inRoot(ToastMatcher())
            .check(matches(isDisplayed()))
    }
}