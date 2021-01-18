package com.jeanjnap.bankapp.util.extension

import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.annotation.StringRes
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.jeanjnap.bankapp.util.matcher.withBackgroundDrawable
import com.jeanjnap.bankapp.util.matcher.withFont
import com.jeanjnap.bankapp.util.matcher.withFontSize
import com.jeanjnap.bankapp.util.matcher.withTextColor
import junit.framework.AssertionFailedError
import org.hamcrest.Matcher
import org.hamcrest.Matchers.not

private fun ViewInteraction.verify(
    matcher: Matcher<View>,
    retryCount: Int
): ViewInteraction {
    withFailureHandler { error, _ ->
        when {
            error !is AssertionFailedError -> throw error
            retryCount > 0 -> {
                perform(waitFor())
                verify(matcher, retryCount - 1)
            }
            else -> throw AssertionFailedError("${error.message} after retries")
        }
    }
    return check(ViewAssertions.matches(matcher))
}

fun ViewInteraction.verify(matcher: Matcher<View>) = verify(matcher, 5)

fun ViewInteraction.verifyText(
    @ColorRes colorId: Int,
    text: String?,
    fontSize: Int,
    @FontRes fontId: Int = 0,
    isDisplayed: Boolean = true
) {
    verify(if (isDisplayed) ViewMatchers.isDisplayed() else not(ViewMatchers.isDisplayed()))
    verify(withTextColor(colorId))
    verify(withText(text))
    verify(withFontSize(fontSize))
    if (fontId != 0) verify(withFont(fontId))
}

fun ViewInteraction.verifyIconText(
    @ColorRes colorId: Int,
    @StringRes textId: Int,
    fontSize: Int,
    isDisplayed: Boolean = true
) {
    verify(if (isDisplayed) ViewMatchers.isDisplayed() else not(ViewMatchers.isDisplayed()))
    verify(withTextColor(colorId))
    verify(withText(textId))
    verify(withFontSize(fontSize))
}

fun ViewInteraction.verifyButton(
    @ColorRes textColorId: Int,
    text: String,
    fontSize: Int,
    @FontRes fontId: Int,
    @DrawableRes drawableId: Int,
    isDisplayed: Boolean = true,
    isEnabled: Boolean = true
) {
    verify(if (isDisplayed) ViewMatchers.isDisplayed() else not(ViewMatchers.isDisplayed()))
    verify(if (isEnabled) ViewMatchers.isEnabled() else not(ViewMatchers.isEnabled()))
    verify(withTextColor(textColorId))
    verify(withText(text))
    verify(withFontSize(fontSize))
    verify(withFont(fontId))
    if (isDisplayed) verify(withBackgroundDrawable(drawableId))
}

private fun waitFor(millis: Long = 200): ViewAction {
    return object : ViewAction {
        override fun getConstraints() = ViewMatchers.isAssignableFrom(View::class.java)

        override fun getDescription() = "Wait for $millis milliseconds."

        override fun perform(uiController: UiController, view: View) {
            uiController.loopMainThreadUntilIdle()
            uiController.loopMainThreadForAtLeast(millis)
        }
    }
}

private fun ViewInteraction.verify(
    assertion: ViewAssertion,
    retryCount: Int
): ViewInteraction {
    withFailureHandler { error, _ ->
        when {
            error !is AssertionFailedError -> throw error
            retryCount > 0 -> {
                perform(waitFor())
                verify(assertion, retryCount - 1)
            }
            else -> throw AssertionFailedError("${error.message} after retries")
        }
    }
    return check(assertion)
}

fun ViewInteraction.verify(assertion: ViewAssertion) = verify(assertion, 5)

private fun ViewInteraction.act(
    action: ViewAction,
    retryCount: Int
): ViewInteraction {
    withFailureHandler { error, _ ->
        when {
            error !is AssertionFailedError -> throw error
            retryCount > 0 -> {
                perform(waitFor(200))
                act(action, retryCount - 1)
            }
            else -> throw AssertionFailedError("${error.message} after retries")
        }
    }
    return perform(action)
}

fun ViewInteraction.act(action: ViewAction) = act(action, 5)