package com.santander.app.core.ui.widget.button

import android.graphics.drawable.Drawable
import android.text.SpannableString
import android.text.Spanned
import android.widget.TextView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable

class SpannableCircularProgress(private val textView: TextView, private val loading: Boolean, text: CharSequence) {

    private val progressDrawable = CircularProgressDrawable(textView.context).apply {
        setStyle(CircularProgressDrawable.LARGE)
        setColorSchemeColors(textView.currentTextColor)
        val size = (centerRadius + strokeWidth).toInt() * 2
        setBounds(0, 0, size, size)
    }

    private val drawableSpan = DrawableSpan(drawable = progressDrawable)

    val spannableString = SpannableString(" ".takeIf { loading } ?: text).apply {
        if (loading) {
            setSpan(drawableSpan, length - 1, length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }

    init {

        progressDrawable.callback = object : Drawable.Callback {
            override fun unscheduleDrawable(who: Drawable, what: Runnable) {
            }

            override fun invalidateDrawable(who: Drawable) {
                textView.invalidate()
            }

            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
            }
        }
        progressDrawable.start()
    }
}