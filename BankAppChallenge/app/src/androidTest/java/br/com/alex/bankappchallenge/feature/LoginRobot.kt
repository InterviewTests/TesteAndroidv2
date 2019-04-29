package br.com.alex.bankappchallenge.feature

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import br.com.alex.bankappchallenge.R
import br.com.alex.bankappchallenge.repository.LoginRepositoryContract
import org.koin.test.KoinTest
import org.koin.test.mock.declareMock
import org.mockito.BDDMockito.given

fun LoginActivityTest.loginActivityTest(
    robot: LoginRobot.() -> Unit
) {
    LoginRobot().apply(robot)
}

class LoginRobot : KoinTest {

    private fun editTextUser() = R.id.editTextUser
    private fun loginButton() = R.id.buttonLogin
    private fun editTextPassword() = R.id.editTextPassword

    fun checkUserFieldHasValue(value: String) {
        onView(withId(editTextUser())).check(matches(withText(value)))
    }

    fun checkErrorMessageOnUserField(message: String) {
        onView(withId(editTextUser())).check(matches(hasErrorText(message)))
    }

    fun checkErrorMessageOnPassowordField(message: String) {
        onView(withId(editTextPassword())).check(matches(hasErrorText(message)))
    }

    fun clickLoginButton() {
        onView(withId(loginButton())).perform(click())
    }

    fun clearUserField() {
        onView(withId(editTextUser())).perform(clearText())
    }

    fun typeTextOnUserField(value: String) {
        onView(withId(editTextUser())).perform(typeText(value))
        closeSoftKeyboard()
    }

    fun typeTextOnPasswordField(value: String) {
        onView(withId(editTextPassword())).perform(typeText(value))
        closeSoftKeyboard()
    }

    fun mockUserSave(user: String) {
        declareMock<LoginRepositoryContract> {
            given(this.getUserLogin()).willReturn(user)
        }
    }
}
