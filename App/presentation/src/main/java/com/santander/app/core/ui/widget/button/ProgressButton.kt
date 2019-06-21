package com.santander.app.core.ui.widget.button

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import com.santander.app.R

class ProgressButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : MaterialButton(context, attrs, defStyleAttr) {

    companion object{
        private const val DRAWABLE_LEFT = 0
    }

    var isLoading = false
        set(value) {
            if (value == isLoading) return
            field = value
            isClickable = !value
            icon = drawables[DRAWABLE_LEFT].takeIf { !value }
            text = SpannableCircularProgress(
                textView = this,
                loading = value,
                text = textDefault
            ).spannableString
        }


    private var textDefault : CharSequence = ""
    private val drawables = mutableListOf<Drawable?>(null, null, null, null)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ProgressButton, defStyleAttr, 0)
            .apply {
                try {

                    textDefault = text.toString()
                    compoundDrawables.forEachIndexed { index, drawable ->
                        drawables[index] = drawable
                    }
                    drawables[DRAWABLE_LEFT] = icon

                    isLoading = getBoolean(R.styleable.ProgressButton_loading, false)

                } finally {
                    recycle()
                }
            }
    }

    override fun setIcon(icon: Drawable?) {
        icon?.run { drawables[DRAWABLE_LEFT] = this }
        super.setIcon(icon)
    }
}