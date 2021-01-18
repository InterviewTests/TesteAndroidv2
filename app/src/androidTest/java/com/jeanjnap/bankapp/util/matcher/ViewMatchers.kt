package com.jeanjnap.bankapp.util.matcher

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.VectorDrawable
import android.view.View
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.jeanjnap.bankapp.util.extension.getContextCompactDrawable
import org.hamcrest.Description
import kotlin.math.roundToInt

fun withBackgroundDrawable(drawableId: Int) =
    object : BoundedMatcher<View, View>(View::class.java) {

        override fun describeTo(description: Description) {
            description.appendText("checking background is $drawableId")
        }

        override fun matchesSafely(item: View): Boolean {
            var expectedDrawable = item.context.getContextCompactDrawable(drawableId)
            var drawable = item.background

            if (drawable == null || expectedDrawable == null) return false

            if (drawable is StateListDrawable && expectedDrawable is StateListDrawable) {
                drawable = drawable.getCurrent()
                expectedDrawable = expectedDrawable.getCurrent()
            }

            if (drawable is ColorDrawable && expectedDrawable is ColorDrawable) {
                return drawable.color == expectedDrawable.color
            }

            if (drawable is BitmapDrawable && expectedDrawable is BitmapDrawable) {
                return drawable.bitmap.sameAs(expectedDrawable.bitmap)
            }

            if (drawable is GradientDrawable && expectedDrawable is GradientDrawable) {
                return drawable.colors?.first() == expectedDrawable.colors?.first() &&
                        drawable.colors?.last() == expectedDrawable.colors?.last()
            }

            if (drawable is VectorDrawable || drawable is VectorDrawableCompat) {
                val drawableRect: Rect = drawable.bounds
                val bitmap = Bitmap.createBitmap(
                    drawableRect.width(),
                    drawableRect.height(),
                    Bitmap.Config.ARGB_8888
                )

                val canvas = Canvas(bitmap)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                drawable.draw(canvas)

                val otherBitmap = Bitmap.createBitmap(
                    drawableRect.width(),
                    drawableRect.height(),
                    Bitmap.Config.ARGB_8888
                )
                val otherCanvas = Canvas(otherBitmap)
                expectedDrawable.setBounds(0, 0, otherCanvas.width, otherCanvas.height)
                expectedDrawable.draw(otherCanvas)

                return bitmap.sameAs(otherBitmap)
            }

            return false
        }
    }

fun withBackgroundColorDrawable(hexString: String) =
    object : BoundedMatcher<View, View>(View::class.java) {

        override fun describeTo(description: Description?) {
            description?.appendText("checking background is $hexString")
        }

        override fun matchesSafely(item: View?) = if (item != null) {
            val desired = Color.parseColor(hexString)
            val background = item.background as? ColorDrawable
            if (background != null) {
                background.color == desired
            } else {
                false
            }
        } else {
            false
        }
    }

fun withWidthSize(width: Int) = object : BoundedMatcher<View, View>(View::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("checking width is $width")
    }

    override fun matchesSafely(item: View?) = if (item != null) {
        val factor = item.context.resources.displayMetrics.density
        val pixels = (width * factor)
        item.width in arrayOf(pixels.toInt(), pixels.roundToInt())
    } else {
        false
    }
}

fun withHeightSize(height: Int) = object : BoundedMatcher<View, View>(View::class.java) {
    override fun describeTo(description: Description?) {
        description?.appendText("checking height is $height")
    }

    override fun matchesSafely(item: View?) = if (item != null) {
        val factor = item.context.resources.displayMetrics.density
        val pixels = (height * factor)
        item.height in arrayOf(pixels.toInt(), pixels.roundToInt())
    } else {
        false
    }
}