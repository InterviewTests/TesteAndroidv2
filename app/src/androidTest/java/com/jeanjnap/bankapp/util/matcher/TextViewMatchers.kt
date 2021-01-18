package com.jeanjnap.bankapp.util.matcher

import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.FontRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description
import kotlin.math.roundToInt

fun withTextColor(@ColorRes colorId: Int) =
    object : BoundedMatcher<View, TextView>(TextView::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("checking textColor is $colorId")
        }

        override fun matchesSafely(item: TextView) =
            item.currentTextColor == ContextCompat.getColor(item.context, colorId)

    }

fun withFontSize(expectedSize: Int) =
    object : BoundedMatcher<View, TextView>(TextView::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("checking textSize is $expectedSize")
        }

        override fun matchesSafely(item: TextView): Boolean {
            val pixels = item.textSize
            val actualSize = (pixels / item.resources.displayMetrics.density).roundToInt()
            return actualSize == expectedSize
        }

    }

fun withFont(@FontRes fontId: Int) = object : BoundedMatcher<View, TextView>(TextView::class.java) {

    override fun describeTo(description: Description) {
        description.appendText("checking font: $fontId")
    }

    override fun matchesSafely(item: TextView): Boolean {
        return item.typeface == ResourcesCompat.getFont(item.context, fontId)
    }
}
