package com.jeanjnap.bankapp.util.extension

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import org.hamcrest.Matcher

fun Matcher<View>.onView(func: ViewInteraction.() -> Unit): ViewInteraction {
    val viewInteraction = Espresso.onView(this)
    viewInteraction.func()
    return viewInteraction
}