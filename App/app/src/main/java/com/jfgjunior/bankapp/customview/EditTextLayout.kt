package com.jfgjunior.bankapp.customview

import android.content.Context
import android.graphics.ColorFilter
import android.util.AttributeSet
import androidx.core.graphics.drawable.DrawableCompat
import com.google.android.material.textfield.TextInputLayout

class EditTextLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextInputLayout(context, attrs, defStyleAttr) {

    override fun setError(error: CharSequence?) {
        val defaultColorFilter = backgroundDefaultColorFilter
        super.setError(error)
        updateBackgroundColorFilter(defaultColorFilter)
    }

    override fun drawableStateChanged() {
        val defaultColorFilter = backgroundDefaultColorFilter
        super.drawableStateChanged()
        updateBackgroundColorFilter(defaultColorFilter)
    }

    private fun updateBackgroundColorFilter(colorFilter: ColorFilter?) {
        if (editText != null && editText!!.background != null) editText!!.background.colorFilter =
            colorFilter
    }

    private val backgroundDefaultColorFilter: ColorFilter?
        get() {
            var defaultColorFilter: ColorFilter? = null
            if (editText != null && editText!!.background != null) defaultColorFilter =
                DrawableCompat.getColorFilter(editText!!.background)
            return defaultColorFilter
        }
}