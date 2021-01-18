package com.jeanjnap.bankapp.util.matcher

import android.view.View
import com.google.android.material.textfield.TextInputLayout
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

fun hasTextInputLayoutError(errorText: String?): Matcher<View> = object : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description?) {
        description?.appendText("checking if error is $errorText")
    }

    override fun matchesSafely(item: View?): Boolean {
        if (item !is TextInputLayout) return false

        return errorText == item.error
    }
}