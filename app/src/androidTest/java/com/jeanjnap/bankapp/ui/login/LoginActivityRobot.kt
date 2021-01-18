package com.jeanjnap.bankapp.ui.login

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withHint
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jeanjnap.bankapp.R
import com.jeanjnap.bankapp.ui.statements.StatementsActivity
import com.jeanjnap.bankapp.util.extension.onView
import com.jeanjnap.bankapp.util.extension.verify
import com.jeanjnap.bankapp.util.extension.verifyText
import com.jeanjnap.bankapp.util.matcher.hasTextInputLayoutError
import com.jeanjnap.bankapp.util.matcher.withDrawable
import com.jeanjnap.bankapp.util.matcher.withWidthSize

fun loginActivityRobot(func: LoginActivityRobot.() -> Unit) = LoginActivityRobot().apply(func)

class LoginActivityRobot {

    fun elementsMustBeConfiguredCorrectly() = apply {
        withId(R.id.iv_logo).onView {
            verify(withWidthSize(124))
            verify(withDrawable(R.drawable.logo))
        }
        withId(R.id.tiet_user).onView {
            verify(withHint("User"))
            verifyText(
                R.color.cadet_blue,
                "",
                15,
                R.font.helvetica_neue
            )
        }
        withId(R.id.tiet_pass).onView {
            verify(withHint("Password"))
            verifyText(
                R.color.cadet_blue,
                "",
                15,
                R.font.helvetica_neue
            )
        }
        withId(R.id.bt_login).onView {
            verify(withWidthSize(190))
            verify(withText("Login"))
        }
    }

    fun verifyUsername(username: String) {
        withId(R.id.tiet_user).onView {
            verify(withHint("User"))
            verifyText(
                R.color.cadet_blue,
                username,
                15,
                R.font.helvetica_neue
            )
        }
    }

    fun typeUser(isValid: Boolean) {
        withId(R.id.tiet_user).onView {
            perform(click())
            perform(typeText(if (isValid) "user@test.com" else "123"))
            perform(pressImeActionButton())
        }
    }

    fun typePass(isValid: Boolean) {
        withId(R.id.tiet_pass).onView {
            perform(click())
            perform(typeText(if (isValid) "aA!" else "123"))
            perform(pressImeActionButton())
        }
    }

    fun hasUserErrorMessage(error: String?) {
        withId(R.id.til_user).onView {
            verify(hasTextInputLayoutError(error))
        }
    }

    fun hasPassErrorMessage(error: String?) {
        withId(R.id.til_pass).onView {
            verify(hasTextInputLayoutError(error))
        }
    }

    fun verifyStatementsScreen() = apply {
        intended(hasComponent(StatementsActivity::class.java.name))
    }
}