package com.jeanjnap.bankapp.util.matcher

import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.Description

fun withDrawable(@DrawableRes drawableId: Int) =
    object : BoundedMatcher<View, ImageView>(ImageView::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("checking Drawable is $drawableId")
        }

        override fun matchesSafely(item: ImageView): Boolean {
            val desired = ResourcesCompat.getDrawable(item.resources, drawableId, null)
            return item.drawable.toBitmap().sameAs(desired?.toBitmap())
        }
    }
